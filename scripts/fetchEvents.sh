#!/bin/sh
SERVER_URL=http://localhost:8080
# ROOT_HTML_DIR=/home/ogoldberg/turfclub.net
ROOT_HTML_DIR=/home/nate/Desktop/test
EVENT_DIR=$ROOT_HTML_DIR/events
curl $SERVER_URL/turf/event/futureEvents > $EVENT_DIR/futureEvents.json
curl $SERVER_URL/turf/event/featuredEvents > $EVENT_DIR/featuredEvents.json
curl $SERVER_URL/turf/event/todaysEvent > $EVENT_DIR/todaysEvent.json
