# AWS Content Delivery and Global Infrastructure

## Amazon CloudFront

### Overview
- Global content delivery network (CDN)
- Low latency content delivery
- 400+ edge locations worldwide
- Integration with AWS services

### Edge Locations and Regional Caches
#### Edge Locations
- Globally distributed cache endpoints
- Serve content to end users
- Lowest latency content delivery
- 400+ locations worldwide

#### Regional Edge Caches
- Larger cache capacity than edge locations
- Serve edge locations
- Reduce origin load
- Better cache hit ratios

#### Point of Presence (PoP)
- Physical locations with edge infrastructure
- Multiple servers per location
- Content caching and delivery
- Network optimization

### CloudFront Distributions
#### Web Distribution
- Static and dynamic content delivery
- HTTP and HTTPS protocols
- Custom domain support (CNAME)
- SSL/TLS certificate integration

#### RTMP Distribution (Deprecated)
- Real-Time Messaging Protocol
- Media streaming
- Being phased out
- Use other streaming solutions

### Origins
#### S3 Bucket as Origin
- Static website hosting
- Origin Access Identity (OAI)
- Origin Access Control (OAC) - newer
- Private bucket access

#### Custom Origins
- Application Load Balancer
- EC2 instances
- On-premises servers
- Any HTTP/HTTPS server

#### Multiple Origins
- Different content from different origins
- Path-based routing
- Origin failover
- Content segregation

### Cache Behaviors
#### Default Cache Behavior
- Applies to all requests
- Path pattern: *
- Origin selection
- Caching settings

#### Additional Cache Behaviors
- Path-specific behaviors
- Override default settings
- Priority-based evaluation
- Multiple origin support

#### Caching Settings
- **TTL (Time To Live)**: Cache duration
- **Cache Headers**: Control caching behavior
- **Query Strings**: Include in cache key
- **Cookies**: Forward to origin or cache

### Security Features
#### SSL/TLS
- Viewer Protocol Policy
- Origin Protocol Policy
- Custom SSL certificates
- AWS Certificate Manager integration

#### Origin Access Identity (OAI)
- Restrict S3 bucket access
- CloudFront-only access
- Legacy method
- Being replaced by OAC

#### Origin Access Control (OAC)
- Enhanced security for S3 origins
- Support for all S3 features
- Server-side encryption
- Recommended over OAI

#### AWS Web Application Firewall (WAF)
- Layer 7 protection
- SQL injection prevention
- Cross-site scripting protection
- Custom rules and rate limiting

#### Signed URLs and Cookies
- Restrict content access
- Time-based access control
- IP-based restrictions
- Custom policy conditions

### Performance Optimization
#### Compression
- Automatic GZIP compression
- Reduce bandwidth usage
- Faster content delivery
- Lower costs

#### HTTP/2 Support
- Multiplexed connections
- Header compression
- Server push
- Better performance

#### Origin Shield
- Additional caching layer
- Reduce origin load
- Improve cache hit ratio
- Single region deployment

### CloudFront Functions
#### Features
- Lightweight JavaScript functions
- Edge-level customization
- Low latency execution
- Cost-effective processing

#### Use Cases
- URL redirects and rewrites
- Request/response header manipulation
- Authentication and authorization
- A/B testing

#### Execution Environment
- Edge locations
- Sub-millisecond execution
- Limited runtime and memory
- No network access

### Lambda@Edge
#### Features
- Full Lambda function capabilities
- Edge and regional edge execution
- Node.js and Python support
- Access to body content

#### Event Types
- **Viewer Request**: After CloudFront receives request
- **Origin Request**: Before CloudFront forwards to origin
- **Origin Response**: After CloudFront receives origin response
- **Viewer Response**: Before CloudFront returns response

#### Use Cases
- User authentication
- Content personalization
- SEO optimization
- Real-time image processing

### Monitoring and Analytics
#### CloudWatch Metrics
- Request count and data transfer
- Error rates (4xx, 5xx)
- Cache hit ratio
- Origin latency

#### Real-Time Logs
- Request-level information
- Fields configuration
- Kinesis Data Streams delivery
- Near real-time delivery

#### Standard Logs
- Detailed access logs
- S3 delivery
- Batch processing
- Historical analysis

### Invalidation
#### Features
- Remove objects from edge caches
- Force cache refresh
- Path-based invalidation
- Wildcard support

#### Cost Considerations
- First 1,000 invalidations free per month
- Additional invalidations charged
- Use versioning instead when possible
- Batch invalidations for efficiency

### Geographic Restrictions
#### Features
- Allowlist or blocklist countries
- IP-based geolocation
- Content licensing compliance
- Regional content delivery

#### Implementation
- CloudFront geographic restrictions
- Third-party geolocation services
- Custom logic with Lambda@Edge
- Combined approaches

## Amazon Route 53

### Overview
- Scalable DNS web service
- Domain registration
- Health checking and failover
- Traffic routing policies

### DNS Record Types
#### Standard Records
- **A**: IPv4 address mapping
- **AAAA**: IPv6 address mapping
- **CNAME**: Canonical name (alias)
- **MX**: Mail exchange
- **TXT**: Text information
- **NS**: Name server
- **PTR**: Pointer (reverse DNS)
- **SRV**: Service locator

#### Route 53 Specific
- **Alias Records**: AWS resource mapping
- Point to AWS resources directly
- No additional charges
- Automatic IP address updates

### Hosted Zones
#### Public Hosted Zones
- DNS for internet-facing domains
- Publicly resolvable
- Global DNS distribution
- Internet routing

#### Private Hosted Zones
- DNS for VPC resources
- VPC-specific resolution
- Multiple VPC association
- Enhanced security

### Routing Policies
#### Simple Routing
- Single resource per record
- No health checks
- Basic DNS resolution
- Default routing policy

#### Weighted Routing
- Distribute traffic by percentages
- Multiple resources per record
- A/B testing and gradual deployments
- Traffic splitting

#### Latency-Based Routing
- Route to lowest latency region
- Improve user experience
- Global application deployment
- Performance optimization

#### Failover Routing
- Active-passive failover
- Primary and secondary resources
- Health check integration
- Disaster recovery

#### Geolocation Routing
- Route based on user location
- Country or continent level
- Content localization
- Compliance requirements

#### Geoproximity Routing
- Route based on geographic proximity
- Bias adjustment capability
- Traffic flow policies
- Fine-grained control

#### Multivalue Answer Routing
- Return multiple healthy endpoints
- Up to 8 healthy records
- Health check integration
- Simple load balancing

### Health Checks
#### Types
- **HTTP/HTTPS**: Web endpoint monitoring
- **TCP**: Port connectivity
- **Calculated**: Combine multiple health checks
- **CloudWatch Alarm**: Metric-based health

#### Features
- Global health checkers
- String matching for HTTP(S)
- Configurable check intervals
- SNS notifications

#### Health Check Locations
- Multiple geographic regions
- Majority consensus for failure
- Avoid false positives
- Regional health monitoring

### Traffic Flow
#### Features
- Visual policy editor
- Complex routing configurations
- Version control
- Global traffic management

#### Policy Elements
- Geolocation and geoproximity
- Latency and weighted routing
- Health checks and failover
- Rule combination

### DNS Security
#### DNSSEC
- Domain name system security
- Cryptographic signatures
- Data integrity verification
- Cache poisoning prevention

#### Route 53 Resolver
- Hybrid DNS resolution
- On-premises integration
- Conditional forwarding
- DNS query logging

### Integration with AWS Services
#### Elastic Load Balancer
- Alias records for ELB
- Automatic IP address updates
- Health check integration
- No additional charges

#### CloudFront
- Alias records for distributions
- Custom domain names
- Certificate integration
- Global content delivery

#### S3 Static Websites
- Alias records for S3 buckets
- Website endpoint mapping
- Cost-effective hosting
- Simple configuration

### Application Recovery Controller (ARC)
#### Features
- Application-level failover
- Readiness checks
- Recovery controls
- Multi-region coordination

#### Components
- **Readiness Check**: Resource readiness verification
- **Recovery Control**: Traffic routing controls
- **Control Panel**: Grouped controls
- **Cluster**: Recovery control grouping

## AWS Global Accelerator

### Overview
- Network service for global traffic optimization
- Improve performance and availability
- Anycast IP addresses
- AWS global network utilization

### How Global Accelerator Works
#### Anycast IP Addresses
- Two static IP addresses
- Routed to optimal edge location
- Single entry point for traffic
- Global traffic distribution

#### AWS Global Network
- Private AWS backbone network
- Reduced internet hops
- Consistent performance
- DDoS protection

#### Edge Locations
- Traffic ingress points
- Route to optimal AWS region
- Performance optimization
- Global presence

### Global Accelerator Components
#### Accelerator
- Top-level resource
- DNS name and IP addresses
- Traffic distribution control
- Performance monitoring

#### Listeners
- Define port and protocol
- TCP and UDP support
- Client affinity options
- Traffic processing rules

#### Endpoint Groups
- Regional endpoint grouping
- Traffic percentage control
- Health checking
- Failover configuration

#### Endpoints
- Resources receiving traffic
- Application Load Balancers
- Network Load Balancers
- EC2 instances
- Elastic IP addresses

### Traffic Management
#### Traffic Dials
- Control traffic to endpoint groups
- Percentage-based routing
- Blue/green deployments
- Gradual traffic shifting

#### Endpoint Weights
- Distribute traffic within endpoint group
- Weighted load balancing
- Fine-grained control
- Performance optimization

#### Client Affinity
- **None**: Route to any healthy endpoint
- **Source IP**: Route based on client IP
- Session persistence
- Application requirements

### Health Checking
#### Health Check Configuration
- Endpoint health monitoring
- Configurable parameters
- Automatic failover
- Performance optimization

#### Failover Behavior
- Automatic unhealthy endpoint removal
- Traffic redistribution
- Recovery detection
- Minimal downtime

### Security Features
#### DDoS Protection
- AWS Shield Standard included
- Automatic mitigation
- Network-level protection
- Application-layer protection

#### TLS Termination
- Endpoint-level TLS termination
- Certificate management
- End-to-end encryption
- Performance optimization

### Use Cases
#### Global Applications
- Multi-region deployments
- Performance optimization
- User experience improvement
- Disaster recovery

#### Gaming Applications
- Low latency requirements
- Global user base
- Real-time performance
- Network optimization

#### IoT Applications
- Device connectivity
- Global device distribution
- Reliable communication
- Network efficiency

### Global Accelerator vs CloudFront
#### Global Accelerator
- TCP/UDP traffic optimization
- Static IP addresses
- Non-HTTP traffic
- Network-level optimization

#### CloudFront
- HTTP/HTTPS content delivery
- Caching at edge locations
- Content optimization
- Application-level features

## AWS Outposts

### Overview
- Fully managed service for on-premises AWS infrastructure
- Consistent hybrid experience
- AWS services on-premises
- Local data processing

### Outposts Configurations
#### Outposts Rack
- 42U rack deployment
- Full rack solution
- Managed by AWS
- Multiple server configurations

#### Outposts Servers
- 1U and 2U server form factors
- Smaller deployments
- Edge locations
- Specific use cases

### AWS Services on Outposts
#### Compute Services
- EC2 instances
- EBS volumes
- ECS containers
- EKS worker nodes

#### Storage Services
- S3 on Outposts
- EBS snapshots
- Local storage
- Data processing

#### Database Services
- RDS on Outposts
- ElastiCache
- Local database processing
- Low latency access

#### Networking
- VPC extension
- Local subnets
- NAT gateways
- Load balancers

### Connectivity
#### Service Link
- Secure connection to AWS Region
- Service management
- Control plane connectivity
- Always-on connection

#### Local Gateway
- On-premises network integration
- Local traffic routing
- Network connectivity
- Hybrid networking

### Management
#### AWS Management Console
- Unified management experience
- Outpost resource visibility
- Service configuration
- Monitoring and alerting

#### Local Operations
- Local data processing
- Reduced latency
- Data residency compliance
- Edge computing

### Use Cases
#### Data Residency
- Local data processing requirements
- Compliance obligations
- Regulatory requirements
- Sensitive data handling

#### Low Latency
- Real-time processing
- Manufacturing applications
- Healthcare systems
- Financial services

#### Local Data Processing
- Edge computing
- Data preprocessing
- Bandwidth optimization
- Cost reduction

## AWS Wavelength

### Overview
- Ultra-low latency applications
- 5G edge computing
- Mobile network integration
- Edge application deployment

### Wavelength Zones
#### Features
- Extension of AWS Regions
- 5G network integration
- Ultra-low latency
- Mobile edge computing

#### Deployment
- Carrier data centers
- 5G network co-location
- Edge infrastructure
- Local processing

### Supported Services
#### Compute
- EC2 instances
- ECS containers
- Application deployment
- Edge processing

#### Networking
- VPC subnets
- Security groups
- Carrier gateways
- Local connectivity

#### Storage
- EBS volumes
- Local storage
- Fast access
- Edge data

### Use Cases
#### Augmented Reality (AR)
- Real-time rendering
- Low latency requirements
- Mobile applications
- Interactive experiences

#### Virtual Reality (VR)
- Immersive experiences
- High bandwidth requirements
- Real-time processing
- Gaming applications

#### IoT Applications
- Real-time data processing
- Edge analytics
- Device connectivity
- Industrial IoT

#### Autonomous Vehicles
- Real-time decision making
- Low latency communication
- Safety-critical applications
- Edge processing

## AWS Local Zones

### Overview
- Extension of AWS Regions
- Single-digit millisecond latency
- Local application deployment
- Media processing and real-time gaming

### Local Zone Features
#### Geographic Proximity
- Closer to end users
- Reduced latency
- Local processing
- Performance optimization

#### Service Availability
- Subset of AWS services
- Compute and storage
- Networking capabilities
- Database services

### Supported Services
#### Compute Services
- EC2 instances
- EBS volumes
- Auto Scaling
- Load balancing

#### Networking
- VPC extensions
- Direct Connect
- Local connectivity
- Hybrid networking

#### Container Services
- ECS tasks
- EKS worker nodes
- Container deployment
- Orchestration

### Use Cases
#### Media Processing
- Video rendering
- Content creation
- Low latency requirements
- High performance computing

#### Real-Time Gaming
- Game server hosting
- Low latency gameplay
- Multiplayer gaming
- Interactive applications

#### Machine Learning
- Inference workloads
- Real-time predictions
- Edge AI applications
- Data processing

## Key Exam Tips

### Content Delivery Strategy
#### CloudFront Use Cases
- Static content delivery
- Dynamic content acceleration
- API acceleration
- Live streaming

#### Cache Optimization
- Appropriate TTL settings
- Cache headers configuration
- Query string and cookie handling
- Invalidation strategies

### DNS and Traffic Management
#### Route 53 Routing Policies
- Simple for basic needs
- Weighted for A/B testing
- Latency-based for performance
- Failover for disaster recovery
- Geolocation for compliance

#### Health Checks
- Monitor endpoint availability
- Integrate with routing policies
- Configure appropriate thresholds
- Use CloudWatch alarms

### Global Infrastructure
#### Edge Services Selection
- CloudFront for content delivery
- Global Accelerator for TCP/UDP optimization
- Route 53 for DNS and traffic routing
- Wavelength for ultra-low latency

#### Regional vs Edge
- Regional services for primary processing
- Edge services for performance optimization
- Hybrid architectures
- Cost considerations

### Performance Optimization
#### Latency Reduction
- Content delivery networks
- Edge computing
- Geographic distribution
- Caching strategies

#### Bandwidth Optimization
- Content compression
- Efficient protocols
- Traffic optimization
- Cost management

### Security Considerations
#### Content Protection
- Signed URLs and cookies
- Geographic restrictions
- WAF integration
- Access controls

#### Network Security
- TLS/SSL encryption
- DDoS protection
- VPC integration
- Security monitoring

### Cost Optimization
#### CDN Costs
- Data transfer charges
- Request charges
- Invalidation costs
- Regional pricing differences

#### Traffic Management
- Routing efficiency
- Bandwidth optimization
- Edge caching
- Origin protection

### Disaster Recovery
#### Multi-Region Strategy
- Route 53 failover routing
- CloudFront origin failover
- Global Accelerator endpoint groups
- Application-level failover

#### Recovery Automation
- Health check integration
- Automatic failover
- Traffic redirection
- Monitoring and alerting
