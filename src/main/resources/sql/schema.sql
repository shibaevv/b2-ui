-- CREATE SCHEMA cfp AUTHORIZATION admin_wow;

-- Order Status
CREATE TABLE status
(
    status_id INTEGER CONSTRAINT status_pk PRIMARY KEY,
    status    VARCHAR(20) NOT NULL
);

-- Transport Mode
CREATE TABLE mode
(
    mode_id   INTEGER CONSTRAINT mode_pk PRIMARY KEY,
    mode      VARCHAR(10) NOT NULL -- Road or Rail
);

CREATE TABLE location
(
    location_id   INTEGER CONSTRAINT location_pk PRIMARY KEY,
    location_code VARCHAR(50) NOT NULL, -- Matches TMS
    location_name VARCHAR(50) NOT NULL, -- Name of Location
    location_type VARCHAR(3)  NOT NULL, -- Required for TMS Upload (HUB or DC)
    address_line  VARCHAR(50) NOT NULL, -- Populates on Connote
    suburb        VARCHAR(50) NOT NULL, -- Populates on Connote
    state         VARCHAR(3)  NOT NULL, -- Populates on Connote
    postcode      VARCHAR(4)  NOT NULL, -- Populates on Connote
    email         VARCHAR(50),
    phone         VARCHAR(10),
    latitude      DECIMAL(10, 8), -- [-90,+90]
    longitude     DECIMAL(11, 8), -- [-180,+180]
    customer_id   INTEGER         -- Optional, will restrict use to one customer
);

CREATE TABLE customer
(
    customer_id                INTEGER CONSTRAINT customer_pk PRIMARY KEY,
    customer_name              VARCHAR(50) NOT NULL,
    customer_code              VARCHAR(50) NOT NULL, -- Customer code reference for TMS
    remit_code                 VARCHAR(50) NOT NULL, -- Remite code - reference for TMS upload
    default_pickup_location_id INTEGER
);

-- Order is special word in SQL (order == shipment OR booking)
CREATE TABLE shipment
(
    shipment_id             INTEGER CONSTRAINT shipment_pk PRIMARY KEY,
    customer_id             INTEGER NOT NULL,
    pickup_location_id      INTEGER NOT NULL,
    destination_location_id INTEGER NOT NULL,
    mode_id                 INTEGER NOT NULL,
    ready_datetime          TIMESTAMP NOT NULL,
    eta_datetime            TIMESTAMP,
    status_id               INTEGER NOT NULL
);

CREATE TABLE booking
(
    booking_id              INTEGER CONSTRAINT booking_pk PRIMARY KEY,
    pickup_location_id      INTEGER NOT NULL,
    pickup_name             VARCHAR(50),
    pickup_datetime         TIMESTAMP NOT NULL,
    destination_location_id INTEGER NOT NULL,
    destination_name        VARCHAR(50),
    connote_number          VARCHAR(50),
    status_id               INTEGER NOT NULL
);


/*
CREATE TABLE IF NOT EXISTS accounts 
(
    user_id INT NOT NULL PRIMARY KEY,
    username VARCHAR ( 50 ) UNIQUE NOT NULL,
    password VARCHAR ( 50 ) NOT NULL,
    email VARCHAR ( 255 ) UNIQUE NOT NULL,
    created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP 
);

CREATE TABLE IF NOT EXISTS roles
(
   role_id int not null primary key,
   role_name varchar (255) unique not null
);

CREATE TABLE IF NOT EXISTS ACCOUNT_ROLES 
(
  USER_ID INT NOT NULL,
  ROLE_ID INT NOT NULL,
  GRANT_DATE TIMESTAMP,
  PRIMARY KEY (USER_ID, ROLE_ID),
  FOREIGN KEY (ROLE_ID)
      REFERENCES ROLES (ROLE_ID),
  FOREIGN KEY (USER_ID)
      REFERENCES ACCOUNTS (USER_ID)
);
*/

ALTER TABLE location ADD CONSTRAINT location_fk1 FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

ALTER TABLE customer ADD CONSTRAINT customer_fk1 FOREIGN KEY (default_pickup_location_id) REFERENCES location(location_id);

ALTER TABLE booking ADD CONSTRAINT booking_fk1 FOREIGN KEY (pickup_location_id) REFERENCES location(location_id);
ALTER TABLE booking ADD CONSTRAINT booking_fk2 FOREIGN KEY (destination_location_id) REFERENCES location(location_id);

ALTER TABLE shipment ADD CONSTRAINT shipment_fk1 FOREIGN KEY (mode_id) REFERENCES mode(mode_id);
ALTER TABLE shipment ADD CONSTRAINT shipment_fk2 FOREIGN KEY (status_id) REFERENCES status(status_id);
