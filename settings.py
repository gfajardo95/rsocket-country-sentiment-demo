import os

from dotenv import load_dotenv
load_dotenv()


PROJECT_NAME = os.environ.get('PROJECT_NAME')
PUBSUB_TOPIC_NAME = os.environ.get('PUBSUB_TOPIC_NAME')
