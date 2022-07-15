# Application to create and search for job openings
This application allows anyone to create job posting and search job openings on multiple criterias.
Below are the technologies used for this project.
### Below are the technologies are used
| Name        | version |
|-------------|---------|
| Java        | 11      |
| spring-boot | 2.7.0   |
| lombok      | 1.18.24 |
| mongodb     | 5.0.9   |
| Docker      |         |

### Below are the APIs
| API        | Type | Remarks                                                                                                                                                                   |
|------------|------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /jobs/add  | POST | create new job posting and it will send notification to only those users who matched with skills, if job doesn't have any skills, it will send notification all the users |
| /jobs/all  | GET  | it accepts optional parameters such as company, skills, min salary, maximum salary, description, job title to filter the job along with pagination option                 |
| /jobs/{id} | GET  | gives job details by specific job id, if job id does not exist, will throw an exception                                                                                   |
| /users/add | POST | creates new user with name, last name, email and optional skills                                                                                                          |

### Current limitation and enhancements:
- while posting new job notification it will send notification immediately to all users with matched skills, this can be bottleneck if we have millions of users. To resolve this, I had planned to create new notifications service using Kafka, so as new job gets posted will post new job id in Kafka topic and users service will pick up the changes and will send emails.
- Also while sending notifications to the user, if any error occurs, it will throw user an error even job posted successfully.
- currently no way to update job posting.
- while making job search, skill set are case-sensitive as it matches with list of skills using `$in` operator. Also, it checks exact number of skills. i.e. Candidate has skill of `Java` and `Python` but job needs only `Java`, it is not sending notification to the users.
- Also while searching for job, it is checking for job.
- Exception handler not working as expected.
- Currently, there is no provisioning to add additional parameters for search criteria.
- when I ran application through docker, facing issue connecting mongodb via mongodbCompass.