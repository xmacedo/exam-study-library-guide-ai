# AWS Monitoring, Logging, and Observability

## Amazon CloudWatch

### Overview
- Monitoring and observability service
- Collect and track metrics
- Monitor logs and set alarms
- React automatically to changes

### CloudWatch Metrics
#### Standard Metrics
- Pre-defined metrics from AWS services
- EC2, RDS, DynamoDB, S3, etc.
- No additional charge
- Basic monitoring frequency

#### Custom Metrics
- Application-specific metrics
- Business metrics
- Custom namespaces
- High-resolution metrics (1-second)

#### Metric Characteristics
- **Namespace**: Container for metrics
- **Metric Name**: Name of the metric
- **Dimensions**: Name/value pairs for filtering
- **Timestamp**: Time when metric was recorded
- **Value**: Numeric value of the metric

#### Metric Resolution
- Standard: 1, 5, 15 minutes, or multiples of 60 seconds
- High-resolution: 1, 5, 10, 30, or 60 seconds
- Custom metrics: Up to 1-second resolution
- Data retention varies by resolution

### CloudWatch Alarms
#### Alarm States
- **OK**: Metric is within threshold
- **ALARM**: Metric exceeds threshold
- **INSUFFICIENT_DATA**: Not enough data to determine state

#### Alarm Actions
- SNS notifications
- Auto Scaling actions
- EC2 actions (stop, terminate, reboot, recover)
- Systems Manager actions

#### Alarm Types
- Static threshold alarms
- Anomaly detection alarms
- Composite alarms (combine multiple alarms)
- Math expression alarms

#### Configuration Options
- Evaluation periods
- Datapoints to alarm
- Missing data treatment
- Alarm evaluation range

### CloudWatch Dashboards
#### Features
- Customizable metric visualizations
- Multiple widget types
- Cross-region monitoring
- Shareable dashboards

#### Widget Types
- Line graphs
- Number displays
- Log insights queries
- Text widgets
- Custom widgets

#### Use Cases
- Real-time monitoring
- Historical analysis
- Executive reporting
- Operational dashboards

### CloudWatch Events (EventBridge)
#### Features
- Near real-time stream of system events
- Rules to route events to targets
- Event patterns and schedules
- AWS service integration

#### Event Sources
- AWS services
- Custom applications
- SaaS partners
- Scheduled events

#### Event Targets
- Lambda functions
- SNS topics
- SQS queues
- Kinesis streams
- Step Functions

### CloudWatch Logs
#### Features
- Centralized log management
- Real-time monitoring
- Log retention policies
- Log analysis and search

#### Log Groups and Streams
- **Log Group**: Collection of log streams
- **Log Stream**: Sequence of log events
- **Log Event**: Record with timestamp and message
- Retention policies per log group

#### Log Insights
- Interactive log analytics
- Query language for log analysis
- Visualization of query results
- Saved queries and dashboards

#### Log Subscriptions
- Real-time log processing
- Filter patterns
- Destination services (Kinesis, Lambda, ElasticSearch)
- Cross-account log sharing

### CloudWatch Agent
#### Features
- Collect additional system metrics
- Collect log files
- Custom metric collection
- Cross-platform support

#### Metrics Collection
- Memory utilization
- Disk space utilization
- Network performance
- Process statistics

#### Configuration
- Configuration file (JSON)
- Parameter Store integration
- Systems Manager integration
- Centralized configuration management

### CloudWatch Synthetics
#### Features
- Automated website monitoring
- API endpoint monitoring
- Canary scripts
- Performance benchmarking

#### Canary Types
- Heartbeat monitoring
- API canaries
- Broken link checker
- Visual monitoring
- Custom canaries

#### Benefits
- Proactive issue detection
- Customer experience monitoring
- Performance baseline establishment
- Alert generation

### CloudWatch Application Insights
#### Features
- Automated application monitoring setup
- Problem detection and notification
- Root cause analysis
- Integration with other AWS services

#### Supported Technologies
- .NET applications on IIS
- Java applications
- SQL Server databases
- Custom applications

## AWS CloudTrail

### Overview
- AWS API logging service
- Governance, compliance, and auditing
- Track user activity and API usage
- Security analysis and compliance

### CloudTrail Features
#### Event Logging
- API calls made to AWS services
- Console actions
- CLI commands
- SDK calls
- Service-to-service calls

#### Event Types
- **Management Events**: Control plane operations
- **Data Events**: Resource operations (S3 object access)
- **Insight Events**: Unusual activity patterns

#### Trail Types
- **All Regions Trail**: Logs events from all regions
- **Single Region Trail**: Logs events from one region
- **Organization Trail**: Logs events for entire organization

### Event Structure
#### Core Elements
- Event time and name
- User identity
- Source IP address
- User agent
- Request parameters
- Response elements

#### Event Sources
- AWS Management Console
- AWS CLI
- AWS SDKs
- AWS services

### CloudTrail Insights
#### Features
- Identify unusual operational activity
- Machine learning-based analysis
- Baseline establishment
- Anomaly detection

#### Insight Types
- Unusual API call patterns
- Error rate anomalies
- Service usage spikes
- Geographic anomalies

### Integration and Analysis
#### CloudWatch Logs Integration
- Send CloudTrail logs to CloudWatch
- Real-time log monitoring
- Metric filters and alarms
- Log analysis with CloudWatch Insights

#### S3 Integration
- Store logs in S3 buckets
- Long-term retention
- Cost-effective storage
- Data lake integration

#### EventBridge Integration
- Real-time event processing
- Event-driven workflows
- Automated responses
- Multi-target event routing

### Security and Compliance
#### Log File Integrity
- Log file validation
- Digest files
- Tampering detection
- Compliance requirements

#### Encryption
- Server-side encryption with KMS
- Log file encryption
- CloudWatch Logs encryption
- End-to-end encryption

#### Access Control
- IAM policies
- S3 bucket policies
- CloudWatch Logs access
- Cross-account access

## AWS X-Ray

### Overview
- Distributed tracing service
- Analyze and debug applications
- Performance optimization
- Service map visualization

### X-Ray Components
#### Traces
- End-to-end request journey
- Timing information
- Error and exception data
- Metadata and annotations

#### Segments
- Work done by single service
- Service-specific information
- Subsegments for granularity
- Timing and error data

#### Service Map
- Visual representation of services
- Service dependencies
- Performance metrics
- Error rates

### X-Ray Integration
#### AWS Services
- API Gateway
- Lambda functions
- Elastic Load Balancer
- EC2 instances
- ECS containers
- Elastic Beanstalk

#### Programming Languages
- Java
- Node.js
- Python
- .NET
- Go
- Ruby

### X-Ray Daemon
#### Features
- Collect trace data from applications
- Buffer and batch traces
- Send to X-Ray service
- Local UDP listener

#### Deployment
- EC2 instances
- ECS containers
- Elastic Beanstalk
- Lambda (automatic)

### Tracing Concepts
#### Sampling
- Control amount of trace data
- Sampling rules
- Cost optimization
- Performance impact reduction

#### Annotations
- Key-value pairs for filtering
- Indexed for search
- Query optimization
- Service categorization

#### Metadata
- Additional trace information
- Not indexed
- Debugging information
- Custom data

### Analysis Features
#### Service Map
- Visual service topology
- Response time analysis
- Error rate monitoring
- Dependency identification

#### Trace Analysis
- End-to-end request flow
- Performance bottlenecks
- Error root cause analysis
- Latency distribution

#### Analytics
- Time series data
- Error analysis
- Performance trends
- Comparative analysis

### Security
#### Encryption
- Encryption in transit
- Data encryption at rest
- KMS integration
- Secure data transmission

#### Access Control
- IAM policies
- Resource-based permissions
- Cross-account access
- Service roles

## Amazon OpenSearch Service (Elasticsearch)

### Overview
- Managed search and analytics engine
- Real-time application monitoring
- Log analytics
- Full-text search

### OpenSearch Features
#### Search Capabilities
- Full-text search
- Structured search
- Geospatial search
- Auto-complete
- Faceted search

#### Analytics
- Real-time analytics
- Log analysis
- Business intelligence
- Data visualization

#### Machine Learning
- Anomaly detection
- Classification
- Regression
- Natural language processing

### Cluster Architecture
#### Nodes
- Master nodes: Cluster coordination
- Data nodes: Store data and handle queries
- Coordinating nodes: Route requests
- Ingest nodes: Pre-process documents

#### Indices and Shards
- Index: Collection of documents
- Shards: Horizontal scaling units
- Replicas: Fault tolerance
- Routing and distribution

### Data Management
#### Document Indexing
- JSON document format
- RESTful API
- Bulk operations
- Real-time indexing

#### Index Lifecycle Management
- Hot, warm, cold, delete phases
- Automatic transitions
- Cost optimization
- Performance optimization

### Security Features
#### Authentication
- Fine-grained access control
- SAML integration
- Active Directory integration
- OpenID Connect

#### Encryption
- Encryption at rest
- Encryption in transit
- Node-to-node encryption
- Field-level encryption

### Monitoring and Alerting
#### Built-in Monitoring
- Cluster health metrics
- Performance metrics
- Error monitoring
- Resource utilization

#### Alerting
- Custom alert conditions
- Multiple notification channels
- Alert history
- Severity levels

### Integration
#### AWS Services
- CloudWatch Logs
- Kinesis Data Streams
- S3
- DynamoDB

#### Kibana/OpenSearch Dashboards
- Data visualization
- Dashboard creation
- Reporting
- User interface

## AWS Systems Manager

### Overview
- Unified interface for AWS operational data
- Automate operational tasks
- Patch management
- Configuration management

### Systems Manager Components
#### Session Manager
- Secure shell access to instances
- No SSH keys or bastion hosts required
- Audit and log sessions
- IAM-based access control

#### Run Command
- Execute commands across multiple instances
- No SSH required
- Command output logging
- Scheduled execution

#### Patch Manager
- Automate operating system patching
- Patch baselines and groups
- Maintenance windows
- Compliance reporting

#### Parameter Store
- Secure configuration data storage
- Hierarchical parameter organization
- Integration with other services
- Version history

#### State Manager
- Maintain consistent configuration
- Apply and monitor configurations
- Compliance tracking
- Drift detection

#### Automation
- Workflow automation
- Runbook execution
- Multi-step processes
- Integration with other services

### Inventory Management
#### Features
- Collect instance metadata
- Software inventory
- Configuration tracking
- Compliance monitoring

#### Data Collection
- Operating system information
- Application inventory
- AWS component data
- Custom inventory types

### Compliance and Governance
#### Compliance Scanning
- Configuration compliance
- Patch compliance
- Custom compliance rules
- Reporting and alerting

#### Change Management
- Change tracking
- Configuration drift
- Change calendars
- Approval workflows

### OpsCenter
#### Features
- Centralized operational issues management
- Incident management
- Automated remediation
- Integration with other AWS services

#### OpsItems
- Operational issues and events
- Status tracking
- Related resources
- Automated responses

## Key Exam Tips

### Monitoring Strategy
#### Service Selection
- CloudWatch for metrics and alarms
- CloudTrail for API auditing
- X-Ray for application tracing
- OpenSearch for log analytics

#### Metric Types
- Use standard metrics when available
- Custom metrics for application-specific data
- High-resolution metrics for detailed monitoring
- Business metrics for operational insights

### Logging Best Practices
#### Log Centralization
- Use CloudWatch Logs for centralization
- Structured logging for analysis
- Log retention policies
- Cost optimization strategies

#### Log Analysis
- CloudWatch Logs Insights for queries
- OpenSearch for complex analytics
- Real-time monitoring with subscriptions
- Alerting on log patterns

### Alerting Strategies
#### Alarm Configuration
- Set appropriate thresholds
- Use multiple evaluation periods
- Handle missing data appropriately
- Implement composite alarms

#### Notification Methods
- SNS for multi-channel notifications
- Auto Scaling for automated responses
- Lambda for custom actions
- Integration with ticketing systems

### Performance Monitoring
#### Application Performance
- X-Ray for distributed tracing
- CloudWatch Application Insights
- Custom metrics for business logic
- End-to-end monitoring

#### Infrastructure Monitoring
- CloudWatch for AWS resources
- Custom metrics for applications
- Synthetic monitoring with CloudWatch Synthetics
- Proactive monitoring strategies

### Security and Compliance
#### Audit Logging
- Enable CloudTrail in all regions
- Log file integrity validation
- Long-term log retention
- Cross-account log access

#### Security Monitoring
- Monitor authentication events
- Track privilege escalation
- API call anomaly detection
- Real-time security alerting

### Cost Optimization
#### Metric Management
- Use metric filters efficiently
- Implement appropriate retention policies
- Archive old logs to S3
- Use sampling for high-volume traces

#### Resource Optimization
- Right-size monitoring infrastructure
- Use appropriate metric resolution
- Implement log lifecycle policies
- Monitor monitoring costs

### Troubleshooting
#### Issue Identification
- Use service maps for dependency visualization
- Correlate metrics across services
- Analyze log patterns
- Track error rates and latency

#### Root Cause Analysis
- X-Ray for request flow analysis
- CloudWatch Logs for error details
- CloudTrail for API call history
- Systems Manager for configuration drift

### Automation
#### Automated Responses
- CloudWatch alarms with Auto Scaling
- EventBridge for event-driven automation
- Lambda for custom remediation
- Systems Manager for operational tasks

#### Operational Automation
- Automated patching with Systems Manager
- Configuration management
- Incident response automation
- Compliance remediation
