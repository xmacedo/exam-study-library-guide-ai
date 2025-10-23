# AWS Compute Services

## Amazon EC2 (Elastic Compute Cloud)

### Overview
- Virtual servers in the cloud
- Scalable computing capacity
- Pay-as-you-go pricing model
- Complete control over instances

### Instance Types
#### General Purpose
- **T3/T4g**: Burstable performance, baseline CPU with burst capability
- **M5/M6i**: Balanced compute, memory, and networking
- **A1**: ARM-based processors, cost-effective

#### Compute Optimized
- **C5/C6i**: High-performance processors for CPU-intensive tasks
- **C6g**: ARM-based, energy efficient

#### Memory Optimized
- **R5/R6i**: High memory-to-vCPU ratio
- **X1e/X2**: High memory instances for in-memory databases
- **z1d**: High frequency and NVMe SSD storage

#### Storage Optimized
- **I3/I4i**: NVMe SSD-backed instance storage
- **D2/D3**: Dense HDD storage
- **H1**: High disk throughput

#### Accelerated Computing
- **P3/P4**: GPU instances for machine learning
- **G4**: GPU instances for graphics workloads
- **F1**: FPGA instances

### Purchasing Options
#### On-Demand Instances
- Pay by hour/second
- No long-term commitment
- Highest cost but maximum flexibility

#### Reserved Instances (RI)
- 1 or 3-year terms
- Up to 75% savings
- **Standard RI**: Cannot change instance type
- **Convertible RI**: Can change instance type
- **Scheduled RI**: Launch within time windows

#### Spot Instances
- Up to 90% savings
- Can be terminated by AWS
- Best for fault-tolerant workloads
- **Spot Fleet**: Collection of Spot Instances

#### Dedicated Hosts
- Physical server dedicated to your use
- Help meet compliance requirements
- More expensive than other options

#### Dedicated Instances
- Instances run on hardware dedicated to a single customer
- May share hardware with other instances from same account

### Placement Groups
#### Cluster Placement Group
- Instances placed in close proximity
- Enhanced networking (10 Gbps)
- Low latency, high throughput

#### Partition Placement Group
- Instances spread across logical partitions
- Each partition has its own rack
- Up to 7 partitions per AZ

#### Spread Placement Group
- Instances placed on distinct hardware
- Maximum 7 instances per AZ
- Reduces correlated failures

### Storage Options
#### Instance Store
- Temporary storage
- High IOPS and throughput
- Data lost when instance stops

#### EBS (Elastic Block Store)
- Persistent block storage
- Independent lifecycle from EC2
- Multiple volume types available

### Networking
#### Elastic Network Interface (ENI)
- Virtual network card
- Can be moved between instances
- Has private IP, security groups

#### Enhanced Networking
- **SR-IOV**: Higher bandwidth, lower latency
- **Elastic Network Adapter (ENA)**: Up to 100 Gbps
- **Intel 82599 VF**: Up to 10 Gbps

#### Elastic IP Address
- Static public IP address
- Can be moved between instances
- Charged when not associated

### Auto Scaling
#### Auto Scaling Groups (ASG)
- Automatically scale EC2 instances
- Health checks and replacement
- Integration with ELB

#### Scaling Policies
- **Target Tracking**: Maintain specific metric
- **Step Scaling**: Scale based on CloudWatch alarms
- **Scheduled Scaling**: Scale at specific times

#### Launch Templates vs Launch Configurations
- **Launch Templates**: Newer, more features, versioning
- **Launch Configurations**: Legacy, immutable

### Security
#### Security Groups
- Virtual firewall for instances
- Stateful (return traffic automatically allowed)
- Allow rules only (no deny rules)
- Default denies all inbound traffic

#### Key Pairs
- Public/private key encryption
- Used for secure login to instances
- AWS stores public key, you keep private key

#### IAM Roles for EC2
- Attach IAM roles to instances
- Temporary security credentials
- No need to store AWS credentials on instance

## AWS Lambda

### Overview
- Serverless compute service
- Run code without managing servers
- Automatic scaling
- Pay for compute time only

### Key Features
#### Event-Driven
- Triggered by various AWS services
- HTTP requests via API Gateway
- File uploads to S3
- Database changes in DynamoDB

#### Automatic Scaling
- Scales from 0 to thousands of executions
- No capacity planning required
- Concurrent execution limits

#### Languages Supported
- Node.js, Python, Java, C#, Go, Ruby
- PowerShell, custom runtimes

### Lambda Function Configuration
#### Memory and CPU
- Memory: 128 MB to 10,240 MB
- CPU scales proportionally with memory
- More memory = faster execution (up to a point)

#### Timeout
- Maximum execution time: 15 minutes
- Default: 3 seconds
- Adjust based on function requirements

#### Environment Variables
- Key-value pairs
- Encrypted at rest
- Available to function code

### Invocation Types
#### Synchronous
- Direct invocation
- Wait for response
- Used by API Gateway, CLI

#### Asynchronous
- Lambda queues request
- Retry on failure
- Used by S3, SNS, SES

#### Stream-based
- Poll-based invocation
- Used by Kinesis, DynamoDB Streams
- Lambda manages polling

### Error Handling
#### Retry Logic
- Synchronous: Client handles retries
- Asynchronous: Lambda retries up to 3 times
- Stream-based: Lambda retries until success or data expires

#### Dead Letter Queues
- Send failed events to SQS or SNS
- Available for asynchronous invocations
- Helps with debugging and monitoring

### Cold Starts
#### What are Cold Starts?
- Initialization time for new execution environment
- Impacts first request latency
- More noticeable with higher memory functions

#### Mitigation Strategies
- Keep functions warm with CloudWatch Events
- Optimize package size
- Use Provisioned Concurrency

### Best Practices
#### Performance
- Minimize package size
- Reuse execution context
- Use connection pooling
- Store secrets in environment variables or Parameter Store

#### Security
- Follow principle of least privilege
- Use IAM roles, not embedded credentials
- Encrypt sensitive data
- Use VPC when accessing private resources

#### Monitoring
- Use CloudWatch Logs and Metrics
- Implement custom metrics
- Use X-Ray for tracing
- Monitor cold start impacts

## Amazon ECS (Elastic Container Service)

### Overview
- Fully managed container orchestration service
- Run Docker containers at scale
- Integration with AWS services
- No additional charges for ECS itself

### Launch Types
#### EC2 Launch Type
- Run containers on EC2 instances you manage
- More control over infrastructure
- Responsible for instance management

#### Fargate Launch Type
- Serverless container platform
- AWS manages infrastructure
- Pay for running tasks only

### ECS Components
#### Clusters
- Logical grouping of tasks or services
- Can span multiple AZs
- Contains EC2 instances or Fargate capacity

#### Task Definitions
- Blueprint for application
- Specifies containers, CPU, memory
- JSON format
- Versioned and immutable

#### Tasks
- Instantiation of task definition
- Can contain multiple containers
- Ephemeral by nature

#### Services
- Maintains desired number of tasks
- Integrates with load balancers
- Handles task replacement

### Networking
#### awsvpc Network Mode
- Each task gets its own ENI
- Better security isolation
- Required for Fargate

#### bridge Network Mode
- Default for EC2 launch type
- Containers share host network

#### host Network Mode
- Container uses host networking
- Performance benefits
- Less isolation

### Service Discovery
#### AWS Cloud Map
- Service registry for cloud resources
- DNS-based service discovery
- Health checking capabilities

### Auto Scaling
#### Service Auto Scaling
- Scale services based on metrics
- Target tracking, step scaling
- Integration with Application Auto Scaling

#### Cluster Auto Scaling
- Scale EC2 instances in cluster
- Based on resource utilization
- Uses Capacity Providers

### Security
#### Task IAM Roles
- Assign IAM roles to tasks
- Fine-grained permissions
- Temporary credentials

#### Secrets Management
- Integration with Secrets Manager
- Environment variables for secrets
- Secure secret injection

## Amazon EKS (Elastic Kubernetes Service)

### Overview
- Managed Kubernetes service
- Runs upstream Kubernetes
- Highly available control plane
- Integration with AWS services

### Architecture
#### Control Plane
- AWS manages master nodes
- Spread across multiple AZs
- Automatic updates and patches

#### Worker Nodes
- EC2 instances or Fargate
- Join cluster via node groups
- Run Kubernetes pods

### Node Groups
#### Managed Node Groups
- AWS manages EC2 instances
- Automatic scaling and updates
- Simplified node management

#### Self-Managed Node Groups
- You manage EC2 instances
- More control and customization
- Manual scaling and updates

#### Fargate Profile
- Serverless compute for pods
- No EC2 instance management
- Pay per pod execution

### Networking
#### Amazon VPC CNI
- Each pod gets VPC IP address
- Direct VPC networking
- Security group per pod

#### Load Balancing
- AWS Load Balancer Controller
- Application Load Balancer for ingress
- Network Load Balancer for services

### Security
#### IAM Roles for Service Accounts (IRSA)
- Map Kubernetes service accounts to IAM roles
- Fine-grained permissions
- No instance-level credentials

#### Pod Security Standards
- Kubernetes-native security policies
- Control pod security context
- Restrict privileged operations

### Add-ons
#### Essential Add-ons
- VPC CNI
- CoreDNS
- kube-proxy

#### Additional Add-ons
- AWS Load Balancer Controller
- Cluster Autoscaler
- Container Insights

## AWS Fargate

### Overview
- Serverless compute engine
- Works with ECS and EKS
- No server management
- Pay for resources used

### Benefits
#### No Infrastructure Management
- No EC2 instances to manage
- No patching or scaling
- Focus on application development

#### Better Security
- Task-level isolation
- Each task has its own kernel
- Reduced attack surface

#### Cost Optimization
- Pay only for running tasks
- No idle instance costs
- Right-sizing capabilities

### Task Sizing
#### CPU and Memory Combinations
- Predefined combinations
- 0.25 vCPU to 4 vCPU
- 0.5 GB to 30 GB memory

#### Storage
- 20 GB ephemeral storage (default)
- Up to 200 GB available
- Encrypted by default

### Networking
#### Task Networking
- Each task gets ENI
- VPC native networking
- Security groups at task level

#### Load Balancing
- Application Load Balancer support
- Network Load Balancer support
- Service mesh integration

### Monitoring
#### CloudWatch Integration
- CPU and memory metrics
- Custom metrics available
- Container insights

#### Logging
- awslogs log driver
- FireLens for advanced routing
- Third-party logging solutions

### Best Practices
#### Right-sizing
- Monitor CPU and memory utilization
- Adjust task definitions accordingly
- Use CloudWatch Container Insights

#### Networking
- Place tasks in private subnets
- Use NAT gateway for internet access
- Implement proper security groups

#### Security
- Use task IAM roles
- Encrypt data in transit and at rest
- Regular security updates

## Key Exam Tips

### When to Use Each Service
#### EC2
- Full control over operating system
- Legacy applications
- Specific instance requirements
- Licensing requirements

#### Lambda
- Event-driven workloads
- Microservices architectures
- Serverless applications
- Short-running tasks (< 15 minutes)

#### ECS
- Containerized applications
- Microservices with AWS integration
- Hybrid architectures
- Cost optimization with containers

#### EKS
- Kubernetes expertise
- Multi-cloud strategy
- Complex orchestration needs
- Kubernetes ecosystem tools

#### Fargate
- Serverless containers
- No infrastructure management
- Variable workloads
- Security isolation requirements

### Common Scenarios
#### High Availability
- Multi-AZ deployments
- Auto Scaling Groups
- Load balancers
- Health checks

#### Cost Optimization
- Right-sizing instances
- Reserved Instances for predictable workloads
- Spot Instances for fault-tolerant workloads
- Serverless for variable workloads

#### Security
- Security groups and NACLs
- IAM roles and policies
- Encryption in transit and at rest
- VPC for network isolation

#### Performance
- Placement groups for low latency
- Enhanced networking
- Instance types for specific workloads
- Auto Scaling for demand fluctuations
