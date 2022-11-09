CREATE TABLE vehicle
(
    vehicle_id          BIGINT AUTO_INCREMENT,
    vehicle_description VARCHAR(45),
    vehicle_details     JSON,

    CONSTRAINT pk_vehicle_id PRIMARY KEY (vehicle_id)
);
