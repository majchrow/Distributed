import random
import threading
import time
from concurrent import futures

from faker import Faker

from pb_python import cv_pb2
from utils import COUNTRIES


class ServantFactory:
    def __init__(self, sleep_time=10):
        Faker.seed(0)
        self.lock = threading.RLock()
        self.sleep_time = sleep_time
        self.observables = {  # All generated csvs per country
            country: [] for country in COUNTRIES
        }
        self.subscriptions = {}  # client -> (country, current event indicator)
        self.fakers = {
            country: Faker(country) for country in COUNTRIES
        }
        self.indicators = {  # all events indicator
            country: 0 for country in COUNTRIES
        }
        self._start_all_generators()

    def _start_all_generators(self):
        executor = futures.ThreadPoolExecutor(max_workers=5)
        for country in COUNTRIES:
            executor.submit(self._create_generator, country)

    def _create_generator(self, country):
        while True:
            new_event = self._generate_event(self.fakers[country])
            self.observables[country].append(new_event)
            self.indicators[country] = len(self.observables[country])
            time.sleep(self.sleep_time)

    def subscribe_on(self, client, event):
        with self.lock:
            self.subscriptions[client] = (event, self.indicators[event])

    def unsubscribe(self, client):
        if client in self.subscriptions:
            with self.lock:
                del self.subscriptions[client]

    def get_client_indicator(self, client):
        return self.subscriptions.get(client, ("", -1))[1]

    def get_client_country(self, client):
        return self.subscriptions.get(client, ("", -1))[0]

    def get_new_events(self, client):  # for example [ already send |<current_indicator>| to be sent | <indicator>]
        if client not in self.subscriptions:
            return []
        with self.lock:
            country, indicator = self.subscriptions.get(client, ("", -1))
            event_indicator = self.indicators.get(country, -1)
            self.subscriptions[client] = (country, event_indicator)
            return self.observables[country][indicator:event_indicator]

    @staticmethod
    def _generate_jobs(faker, number):
        return [cv_pb2.Job(
            job=faker.job(),
            company=faker.company()
        ) for _ in range(number)]

    @staticmethod
    def _generate_person(faker):
        return cv_pb2.Person(
            firstName=faker.first_name(),
            secondName=faker.last_name(),
            birth=ServantFactory._generate_birth(faker),
            email=faker.ascii_email(),
            phoneNumber=faker.phone_number()
        )

    @staticmethod
    def _generate_birth(faker):
        choices = [cv_pb2.Poland, cv_pb2.France, cv_pb2.Germany, cv_pb2.Japan, cv_pb2.US]
        return cv_pb2.Birth(
            country=random.choice(choices),
            date=faker.date()

        )

    @staticmethod
    def _generate_event(faker):
        return cv_pb2.CurriculumVitae(
            person=ServantFactory._generate_person(faker),
            previousJobs=ServantFactory._generate_jobs(faker, random.randint(1, 5)),
            description=faker.text()
        )
