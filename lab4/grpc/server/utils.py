import logging
import os
import time

COUNTRIES = ["pl_PL",
             "en_US",
             "de_DE",
             "fr_FR",
             "ja_JP"]


def enable_logger(directory="logs", filename="logfile", extension=".log", level=logging.INFO):
    logging_directory = os.path.join(os.getcwd(), directory)
    if not os.path.exists(logging_directory):
        os.makedirs(logging_directory)
    timestamp = str(time.time()).split('.')[0]
    filename = filename + str(timestamp) + extension
    logfile = os.path.join(logging_directory, filename)
    logging.basicConfig(
        level=level,
        format="[Server]: %(asctime)s [%(levelname)s] %(message)s",
        handlers=[
            logging.FileHandler(logfile),
            logging.StreamHandler()
        ]
    )

