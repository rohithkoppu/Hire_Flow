## Recommended Test Order

1. Start PostgreSQL.
2. Start Discovery Server.
3. Start Auth Service.
4. Start Candidate Service.
5. Start Interview Service.
6. Start API Gateway.
7. Check Eureka.
8. Generate an admin JWT token.
9. Authorize Candidate Swagger.
10. Create and test a candidate.
11. Authorize Interview Swagger.
12. Create and test an interview.
13. Cancel the interview.
14. Soft-delete the candidate.
15. Check PostgreSQL records.
16. Check application logs.

## Service Ports

| Service | Port |

| Discovery Server | 8761 |
| Auth Service | 8080 |
| Candidate Service | 8081 |
| Interview Service | 8082 |
| API Gateway | 8085 |
| PostgreSQL | 5432 |

Start the applications in this order:

1. `DiscoveryServerApplication.java`
2. `AuthApplication.java`
3. `CandidateApplication.java`
4. `InterviewApplication.java`
5. `ApiGatewayApplication.java`

## Eureka Dashboard Open: http://localhost:8761

## Swagger URLs
Auth Service:
http://localhost:8080/swagger-ui/index.html
Candidate Service:
http://localhost:8081/swagger-ui/index.html
Interview Service:
http://localhost:8082/swagger-ui/index.html
