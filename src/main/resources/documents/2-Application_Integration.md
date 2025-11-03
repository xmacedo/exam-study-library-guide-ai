# AWS Application Integration Services

## Amazon Simple Queue Service (SQS)

### Overview
- Fully managed message queuing service
- Decouple and scale microservices and serverless applications
- Reliable message delivery
- No message loss or duplication

### Queue Types
#### Standard Queues
- Nearly unlimited throughput
- At-least-once delivery (duplicates possible)
- Best-effort ordering
- Default choice for most use cases

#### FIFO Queues
- First-In-First-Out delivery order
- Exactly-once processing
- Up to 300 transactions per second
- Message deduplication

### Key Features
#### Message Attributes
- Name-value pairs with messages
- Up to 10 attributes per message
- String, Number, or Binary data types
- Useful for message filtering

#### Message Visibility Timeout
- Time message is invisible after being received
- Default: 30 seconds (configurable)
- Prevents multiple consumers processing same message
- Can be extended by consumer

#### Dead Letter Queues (DLQ)
- Handle messages that can't be processed
- Separate queue for failed messages
- Configurable maxReceiveCount
- Helps with debugging and monitoring

#### Long Polling
- Wait for messages to arrive
- Reduce API calls and costs
- 1-20 second wait time
- More efficient than short polling

### Security Features
#### Access Control
- IAM policies for API access
- SQS resource policies
- Cross-account access
- Service-to-service access

#### Encryption
- Server-side encryption with KMS
- Client-side encryption
- Encryption in transit (HTTPS)
- Message body and attributes

### Message Lifecycle
#### Send Message
- Messages up to 256 KB
- Larger payloads via S3 (extended client library)
- JSON, XML, or unformatted text
- Automatic acknowledgment

#### Receive Message
- Polling-based message retrieval
- Batch operations (up to 10 messages)
- Message becomes invisible to other consumers
- Manual deletion required

#### Delete Message
- Explicit deletion after processing
- Prevents message reprocessing
- Automatic deletion if not deleted within retention period
- Receipt handle required for deletion

### Performance and Scaling
#### Throughput
- Standard queues: Nearly unlimited
- FIFO queues: 300 TPS (3000 with batching)
- Automatic scaling
- No pre-provisioning required

#### Batching
- Send up to 10 messages in single request
- Receive up to 10 messages in single request
- Reduce API calls
- Improve cost efficiency

### Use Cases
#### Decoupling Applications
- Separate application components
- Handle traffic spikes
- Improve fault tolerance
- Enable independent scaling

#### Work Queues
- Distribute work among workers
- Process jobs asynchronously
- Handle variable workloads
- Retry failed operations

#### Event-Driven Architecture
- React to events asynchronously
- Process events independently
- Scale event processing
- Handle event ordering (FIFO)

## Amazon Simple Notification Service (SNS)

### Overview
- Fully managed pub/sub messaging service
- Decouple microservices and serverless applications
- Fan-out messages to multiple subscribers
- Push-based message delivery

### Publishing Methods
#### Direct Publishing
- Publish directly to topic
- One message to multiple subscribers
- Immediate delivery attempt
- Synchronous operation

#### Fan-Out Pattern
- SNS topic triggers multiple SQS queues
- Parallel processing
- Reliable message delivery
- Decoupled architecture

### Subscription Protocols
#### Email/Email-JSON
- Human-readable notifications
- JSON format for structured data
- Confirmation required
- Useful for alerts and notifications

#### SMS
- Text message notifications
- Mobile phone delivery
- Character limits apply
- Global SMS support

#### HTTP/HTTPS
- Webhook delivery
- REST endpoint notifications
- Custom application integration
- Retry logic included

#### SQS
- Message queuing integration
- Reliable message delivery
- Dead letter queue support
- Fan-out architecture

#### Lambda
- Serverless function triggers
- Event-driven processing
- Automatic scaling
- Real-time processing

#### Platform Endpoints
- Mobile push notifications
- iOS, Android, Windows platforms
- Device-specific messaging
- Application endpoint management

### Message Features
#### Message Filtering
- Filter messages by attributes
- Subscription-level filtering
- JSON policy documents
- Reduce irrelevant message delivery

#### Message Attributes
- Name-value pairs with messages
- Up to 10 attributes per message
- Used for filtering and routing
- String, Number, or Binary types

#### Message Deduplication (FIFO)
- Prevent duplicate messages
- Content-based or token-based
- 5-minute deduplication interval
- FIFO topics only

### SNS FIFO Topics
#### Features
- Strict message ordering
- Exactly-once message delivery
- Message deduplication
- Compatible with SQS FIFO queues

#### Group IDs
- Preserve ordering within groups
- Parallel processing across groups
- Load balancing capability
- Scalability improvement

### Security Features
#### Access Control
- IAM policies
- Topic policies
- Cross-account publishing
- Service-to-service access

#### Encryption
- Server-side encryption with KMS
- Message encryption
- Delivery status logging
- Compliance support

### Delivery Status Logging
#### Supported Protocols
- HTTP/HTTPS endpoints
- Lambda functions
- SQS queues
- Platform applications

#### CloudWatch Logs Integration
- Success and failure logs
- Delivery attempt tracking
- Error analysis
- Performance monitoring

### Mobile Push Notifications
#### Platform Support
- Apple Push Notification Service (APNs)
- Google Cloud Messaging (GCM)
- Amazon Device Messaging (ADM)
- Microsoft Push Notification Service (MPNS)

#### Application Management
- Platform application registration
- Device endpoint management
- Message targeting
- Delivery feedback

## Amazon EventBridge

### Overview
- Serverless event bus service
- Connect applications using events
- Route events from sources to targets
- Event-driven architecture foundation

### Event Sources
#### AWS Services
- Over 90 AWS services as event sources
- EC2, S3, DynamoDB, RDS events
- Automatic event generation
- Service-specific event schemas

#### Custom Applications
- Custom event sources
- API-based event publishing
- Application-generated events
- Business logic events

#### SaaS Partners
- Third-party SaaS applications
- Salesforce, Shopify, Zendesk
- Partner event sources
- Pre-built integrations

### Event Buses
#### Default Event Bus
- AWS service events
- Always available
- No additional setup required
- Service event routing

#### Custom Event Buses
- Application-specific events
- Cross-account event sharing
- Resource policies
- Organizational event buses

#### Partner Event Buses
- SaaS partner events
- Pre-configured integrations
- Partner-managed events
- Simplified setup

### Event Rules
#### Event Pattern Matching
- JSON-based event patterns
- Content-based filtering
- Multiple pattern support
- Complex matching logic

#### Schedule-Based Rules
- Cron expressions
- Rate expressions
- Scheduled event generation
- Time-based automation

#### Target Configuration
- Multiple targets per rule
- Target-specific configuration
- Error handling settings
- Retry policies

### Event Targets
#### AWS Services
- Lambda functions
- SQS queues
- SNS topics
- Kinesis streams
- Step Functions
- ECS tasks

#### API Destinations
- HTTP endpoints
- Third-party APIs
- Custom applications
- Webhook integrations

#### Cross-Account Targets
- Event routing across accounts
- Resource-based policies
- Organizational integration
- Centralized event processing

### Event Schemas
#### Schema Registry
- Event schema management
- Schema versioning
- Code generation
- Schema evolution

#### Schema Discovery
- Automatic schema inference
- Event structure analysis
- Schema recommendations
- Development acceleration

### Event Replay
#### Features
- Replay historical events
- Time range specification
- Target replay configuration
- Testing and recovery

#### Use Cases
- Application testing
- Bug reproduction
- Data recovery
- System debugging

### Archive and Replay
#### Event Archive
- Long-term event storage
- Configurable retention
- Compression and encryption
- Cost-effective storage

#### Replay Configuration
- Source archive selection
- Target destination
- Time range filtering
- Event pattern filtering

## AWS Step Functions

### Overview
- Visual workflow orchestration service
- Coordinate distributed applications
- State machine-based execution
- Serverless workflow management

### State Machine Types
#### Standard Workflows
- Long-running workflows
- Exactly-once execution
- Full execution history
- Audit and debugging support

#### Express Workflows
- High-volume, short-duration workflows
- At-least-once execution
- Lower cost per execution
- IoT and streaming use cases

### State Types
#### Task State
- Execute work using resources
- Lambda functions, activities
- Service integrations
- Error handling

#### Choice State
- Branch execution based on input
- Multiple choice rules
- Default fallback path
- Conditional logic

#### Wait State
- Delay execution
- Fixed time delay
- Timestamp-based delay
- Input-based delay

#### Parallel State
- Execute branches in parallel
- Wait for all branches to complete
- Result aggregation
- Independent execution paths

#### Map State
- Process array items
- Parallel item processing
- Iterator configuration
- Dynamic parallelism

#### Pass State
- Pass input to output
- Transform data
- Inject fixed values
- Testing and development

#### Fail/Succeed States
- Terminal states
- Workflow completion
- Error conditions
- Success conditions

### Service Integrations
#### AWS Service Integration
- Direct service API calls
- Lambda, SNS, SQS, DynamoDB
- Batch, ECS, Glue
- No Lambda wrapper required

#### Optimized Integrations
- Native service support
- Simplified configuration
- Error handling
- Retry logic

#### SDK Integrations
- AWS SDK service calls
- All AWS services supported
- Request/response patterns
- Flexible integration

### Error Handling
#### Retry Configuration
- Retry policies per state
- Exponential backoff
- Maximum retry attempts
- Interval configuration

#### Catch Configuration
- Error type matching
- Fallback states
- Error state transitions
- Error information passing

#### Error Types
- States.ALL (catch all errors)
- States.Timeout
- States.TaskFailed
- Custom error types

### Input/Output Processing
#### Input Path
- Filter input data
- JSONPath expressions
- Data selection
- State input preparation

#### Output Path
- Filter output data
- Result transformation
- Data extraction
- Next state input

#### Result Path
- Combine input and output
- Preserve original input
- Merge operation results
- Data flow control

### Activity Tasks
#### Features
- Custom worker implementation
- Polling-based task execution
- Long-running workers
- Manual task management

#### Use Cases
- Legacy system integration
- Custom processing logic
- Long-running operations
- External system coordination

### Monitoring and Debugging
#### Execution History
- Step-by-step execution details
- Input/output at each step
- Timing information
- Error details

#### CloudWatch Integration
- Execution metrics
- Custom metrics
- Alarms and notifications
- Performance monitoring

#### X-Ray Integration
- Distributed tracing
- Service map visualization
- Performance analysis
- Error tracking

## Amazon Simple Workflow Service (SWF)

### Overview
- Workflow coordination service
- Task-based workflow management
- Guaranteed execution order
- No duplicate tasks

### SWF Components
#### Workflow
- Sequence of steps to complete process
- Coordination logic
- Decision making
- Task scheduling

#### Activities
- Single step in workflow
- Application-specific tasks
- Worker-executed operations
- Atomic work units

#### Tasks
- Work assignments
- Activity tasks for workers
- Decision tasks for deciders
- Distributed task execution

#### Workers
- Programs that receive and process tasks
- Activity workers for activities
- Deciders for workflow logic
- Polling-based task retrieval

### SWF vs Step Functions
#### SWF Characteristics
- Code-based workflow definition
- External workers
- More complex setup
- Legacy service

#### Step Functions Advantages
- Visual workflow designer
- Serverless execution
- Easier development
- Modern service integrations

#### Migration Considerations
- Step Functions recommended for new workflows
- SWF for existing complex workflows
- Feature comparison analysis
- Migration path planning

## Amazon MQ

### Overview
- Managed message broker service
- Apache ActiveMQ and RabbitMQ
- Lift-and-shift migration
- Industry-standard protocols

### Supported Engines
#### Apache ActiveMQ
- JMS API support
- MQTT, AMQP, STOMP protocols
- Enterprise messaging patterns
- Java application integration

#### RabbitMQ
- AMQP 0-9-1 protocol
- Advanced routing capabilities
- Management UI
- Plugin ecosystem

### Deployment Options
#### Single-Instance Broker
- Development and testing
- Lower cost
- No high availability
- Simple setup

#### Active/Standby Broker
- High availability
- Automatic failover
- Shared storage
- Production workloads

#### Cluster Deployment (RabbitMQ)
- Horizontal scaling
- Load distribution
- Queue mirroring
- Enhanced availability

### Migration Considerations
#### From On-Premises
- Protocol compatibility
- Message persistence
- Client library support
- Configuration migration

#### Amazon MQ vs SQS/SNS
- Protocol requirements
- Message ordering needs
- Transaction support
- Integration complexity

### Security Features
#### Network Security
- VPC deployment
- Security groups
- Private access
- TLS encryption

#### Authentication
- LDAP integration
- Built-in user database
- Certificate-based auth
- Role-based access

## Application Integration Patterns

### Event-Driven Architecture
#### Components
- Event producers
- Event routers (EventBridge)
- Event consumers
- Event stores

#### Benefits
- Loose coupling
- Scalability
- Fault tolerance
- Flexibility

### Microservices Communication
#### Synchronous Communication
- API Gateway + Lambda
- Application Load Balancer
- Service discovery
- Circuit breaker patterns

#### Asynchronous Communication
- SQS for reliable messaging
- SNS for pub/sub patterns
- EventBridge for event routing
- Step Functions for orchestration

### Batch Processing Patterns
#### SQS + Lambda
- Event-driven batch processing
- Automatic scaling
- Error handling
- Cost optimization

#### SQS + ECS/EC2
- Long-running batch jobs
- Container-based processing
- Custom worker logic
- Persistent workers

### Fan-Out Patterns
#### SNS + SQS
- Message duplication
- Parallel processing
- Different processing logic
- Reliable delivery

#### EventBridge + Multiple Targets
- Event routing
- Content-based routing
- Service decoupling
- Error handling

## Key Exam Tips

### Service Selection Criteria
#### SQS Use Cases
- Point-to-point communication
- Reliable message delivery
- Decoupling applications
- Work queue patterns

#### SNS Use Cases
- Pub/sub messaging
- Fan-out patterns
- Mobile notifications
- Email/SMS alerts

#### EventBridge Use Cases
- Event-driven architectures
- SaaS integration
- Cross-account events
- Event routing and filtering

#### Step Functions Use Cases
- Workflow orchestration
- Complex business processes
- Error handling and retries
- Service coordination

### Performance Considerations
#### Message Throughput
- Standard SQS: Nearly unlimited
- FIFO SQS: 300 TPS (3000 with batching)
- SNS: No published limits
- Batching for efficiency

#### Latency Requirements
- SQS: Polling-based (higher latency)
- SNS: Push-based (lower latency)
- EventBridge: Near real-time
- Step Functions: Workflow coordination overhead

### Security Best Practices
#### Access Control
- IAM policies for service access
- Resource-based policies
- Cross-account access patterns
- Principle of least privilege

#### Data Protection
- Encryption at rest and in transit
- Message filtering for sensitive data
- VPC endpoints for private access
- Audit logging

### Cost Optimization
#### Message Patterns
- Long polling vs short polling
- Batch operations
- Message filtering
- Dead letter queues

#### Service Selection
- Choose appropriate service for use case
- Consider throughput requirements
- Evaluate latency needs
- Monitor usage patterns

### Common Integration Patterns
#### Request/Response
- API Gateway + Lambda
- Synchronous processing
- Immediate response required
- Direct integration

#### Fire and Forget
- SNS for notifications
- SQS for background processing
- Asynchronous execution
- No response required

#### Event Sourcing
- EventBridge for event storage
- Event replay capabilities
- Audit trail maintenance
- System state reconstruction

#### Workflow Orchestration
- Step Functions for complex flows
- Error handling and retries
- State management
- Service coordination
