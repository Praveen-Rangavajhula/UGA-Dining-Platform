# UGA-Dining-Platform
An online platform for UGA students and staff to conveniently access and view dining hall menus across all Dining Commons.

## How to Set Up Your Local Configuration:

1. Copy the [`template.env`](template.env) file.
2. Rename the copied file to `.env`.

### Local Configuration Details (Add these to `.env` file):

- `POSTGRES_USERNAME=` (Your PostgreSQL username)
- `POSTGRES_PASSWORD=` (Your PostgreSQL password)
- `POSTGRES_URL=jdbc:postgresql://localhost:15432/uga_dining` (Update if using a different PostgreSQL URL)
- `POSTGRES_DB=uga_dining` (Your PostgreSQL database name)
- `PGADMIN_DEFAULT_EMAIL=admin@example.com` (Default email for pgAdmin)
- `PGADMIN_DEFAULT_PASSWORD=admin` (Default password for pgAdmin)

**Generate a new secret key:**
Use an online random secret key generator to create a 256-bit (32 Byte Hex) secret key. Replace `SECRET_KEY=` in the `.env` file with your generated key.

## Docker Setup:

Ensure Docker Desktop is installed on your machine before proceeding with the setup. The platform utilizes Docker Compose for container orchestration.

1. Install Docker Desktop from [Docker's official website](https://www.docker.com/products/docker-desktop/).
2. After installing Docker Desktop, use the provided Docker Compose file to set up the required containers for the UGA Dining Platform.

**Note:** Setting up containers manually is not essential, as they are configured by default. The project uses Spring Boot Docker support, automatically detecting the Docker Compose file and running the required containers.

And you're good to go! Navigate to the [`UgaDiningCommonsApplication.java`](src/main/java/com/db/project/ugadining/UgaDiningCommonsApplication.java) file in your project.
