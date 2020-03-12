if [[ -d ".venv" ]]
then
	rm -rf .venv
fi
python3 -m venv .venv
source .venv/bin/activate
pip install -r server/requirements.txt
python3 server/main.py
