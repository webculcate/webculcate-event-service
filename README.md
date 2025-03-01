# webculcate event service

## Description
This service manages the life cycle of events, venues, and event schedules. In the early stage of this project, I kept these domains under single service but we can segregate these into different services based on new requirements.

## Architecture
<img width="1411" alt="webculcateArchitecture" src="https://github.com/user-attachments/assets/ea1e00f4-6c59-430f-ad5d-276b49967334" />


## Functionalities
1. Event
   * create event
   * update event
   * fetch event
2. Venue
   * create venue
   * update venue
   * fetch venue
3. ScheduledEvent
   * create event schedule
   * update event schedule
   * update capacity
   * fetch event schedule
   * get conflicts with existing schedules
