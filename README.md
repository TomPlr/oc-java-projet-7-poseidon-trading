# Poseidon Trading

Poseidon Trading is a Java-based application designed to manage trading operations. This repository contains the source code and necessary configurations for the project.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Management**: Create, read, update, and delete user information.
- **Trade Management**: Manage trades including adding, updating, and deleting trades.
- **Rule Management**: Define and manage rules for trading operations.
- **Rating Management**: Manage ratings for different entities.
- **CurvePoint Management**: Manage curve points for financial analysis.
- **BidList Management**: Manage bid lists for trading operations.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:
- Java Development Kit (JDK) 11 or later
- Maven
- An Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse

### Installation

1. **Clone the repository**:
   ```sh
   git clone https://github.com/TomPlr/oc-java-projet-7-poseidon-trading.git
   
2. **Navigate to the project directory**:
   ```sh
   cd oc-java-projet-7-poseidon-trading
   
3. **Build the project**:
   ```sh
   mvn clean install
   
4. **Run the application**:
   ```sh
   mvn spring-boot:run
   
## Usage

Once the application is running, you can access the API endpoints to perform various operations. Below are some example endpoints:

- **User Management**:
  - `GET /users`: Retrieve all users.
  - `GET /users/{id}`: Retrieve a user by ID.
  - `POST /users`: Add a new user.
  - `PUT /users/{id}`: Update an existing user.
  - `DELETE /users/{id}`: Delete a user by ID.

- **Trade Management**:
  - `GET /trades`: Retrieve all trades.
  - `GET /trades/{id}`: Retrieve a trade by ID.
  - `POST /trades`: Add a new trade.
  - `PUT /trades/{id}`: Update an existing trade.
  - `DELETE /trades/{id}`: Delete a trade by ID.

- **Rule Management**:
  - `GET /rules`: Retrieve all rules.
  - `GET /rules/{id}`: Retrieve a rule by ID.
  - `POST /rules`: Add a new rule.
  - `PUT /rules/{id}`: Update an existing rule.
  - `DELETE /rules/{id}`: Delete a rule by ID.

- **Rating Management**:
  - `GET /ratings`: Retrieve all ratings.
  - `GET /ratings/{id}`: Retrieve a rating by ID.
  - `POST /ratings`: Add a new rating.
  - `PUT /ratings/{id}`: Update an existing rating.
  - `DELETE /ratings/{id}`: Delete a rating by ID.

- **CurvePoint Management**:
  - `GET /curvepoints`: Retrieve all curve points.
  - `GET /curvepoints/{id}`: Retrieve a curve point by ID.
  - `POST /curvepoints`: Add a new curve point.
  - `PUT /curvepoints/{id}`: Update an existing curve point.
  - `DELETE /curvepoints/{id}`: Delete a curve point by ID.

- **BidList Management**:
  - `GET /bidlists`: Retrieve all bid lists.
  - `GET /bidlists/{id}`: Retrieve a bid list by ID.
  - `POST /bidlists`: Add a new bid list.
  - `PUT /bidlists/{id}`: Update an existing bid list.
  - `DELETE /bidlists/{id}`: Delete a bid list by ID.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.
