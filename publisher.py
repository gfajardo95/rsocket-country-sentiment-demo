import time

from google.cloud import pubsub_v1

import settings


# publishes the country sentiments to a Pub/Sub topic
def publish(lines):
    client = pubsub_v1.PublisherClient()
    topic_path = client.topic_path(settings.PROJECT_NAME, settings.PUBSUB_TOPIC_NAME)

    for line in lines:
        client.publish(topic_path, line.encode("utf-8"))
        time.sleep(1)


if __name__ == '__main__':
    # read from file
    with open('country-sentiments.txt', 'r') as f:
        publish(f.readlines())