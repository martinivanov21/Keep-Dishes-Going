# < KeepDishesGoing >

## Challenges & Accomplishments

##### Challenges
- Trying to maintain the different bounded context
- Having to maintain with a lot of classes.
- Confusion with multiple implementations


##### The aspects of your project you are most proud of.
- The connection between the bounded context works fine
- Information between the bounded contexts is established fine
- Successfully maintaining multiple implementation of Ports
- 

---

## ✅ Finished Features

List all features that were successfully implemented and tested.

- [x] **Event Sourcing with Snapshots**
    - At least one **snapshotted, event-sourced aggregate**: `Restaurant`
- [x] **Security on REST Endpoints**
    - `OWNER` role required for certain mutations in the Restaurant bounded context
- [x] **DDD Concepts**
    - Entities, Aggregates, Value Objects, Domain Events (rich domain model)
- [x] **Context Mapping Between Bounded Contexts**
    - `Restaurant` ↔ `Menu` ↔ `Dish` via event listeners
- [x] **Hexagonal Architecture**
    - Clear separation of domain, application (use cases), and adapters
- [x] **Commands / Events Implemented**
    - Command handlers invoke use cases; domain events emitted
- [x] **Ports in Use**
    - Repositories, Event Store defined as ports
- [x] **EventListeners**
    - Defined multiple EventListeners
- [x] **Clear JPA Mapping**
    - JPA for non-ES aggregates/read models and projections
- [x] **Publishing Dishes**
    - Clear communication between BC for Dish and Restaurant and Menu
- [x] **Creating different Objects**
    - Can create and sustain Objects between the BC
---

## ❌ Unfinished / Planned Features

List features that are planned, in progress, or not yet implemented.

Example:
- [ ] Price evolution
- [ ] RabbitMQ
- [ ] Handling Order Progress between the BC

