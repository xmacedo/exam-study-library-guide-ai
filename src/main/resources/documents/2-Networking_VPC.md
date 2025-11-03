# AWS Networking and VPC

## Amazon VPC (Virtual Private Cloud)

### Overview
- Virtual network dedicated to your AWS account
- Logically isolated from other virtual networks
- Complete control over virtual networking environment
- Launch AWS resources in defined virtual network

### VPC Components
#### Subnets
- Range of IP addresses in your VPC
- Reside in single Availability Zone
- Public subnets: Route to Internet Gateway
- Private subnets: No direct route to internet

#### Route Tables
- Set of rules (routes) for network traffic direction
- Each subnet must be associated with route table
- Local route for VPC communication always present
- Can have multiple custom route tables

#### Internet Gateway (IGW)
- Horizontally scaled, redundant, highly available
- Allows communication between VPC and internet
- Provides target for internet-routable traffic
- Performs NAT for instances with public IP addresses

#### NAT Gateway
- Managed NAT service
- Enable internet access for private subnet resources
- Highly available within single AZ
- Bandwidth scales up to 45 Gbps

#### NAT Instance
- User-managed NAT solution
- EC2 instance in public subnet
- More configuration required
- Can be used as bastion host

### IP Addressing
#### IPv4 CIDR Blocks
- Primary CIDR block: required when creating VPC
- Secondary CIDR blocks: can be added later
- Size: /16 to /28 netmask
- Cannot overlap with existing CIDR blocks

#### IPv6 CIDR Blocks
- Amazon-provided IPv6 CIDR block
- /56 prefix assigned to VPC
- /64 prefix assigned to subnets
- Globally unique addresses

#### Private IP Addresses
- Assigned from subnet CIDR block
- Primary private IP: persistent during instance lifetime
- Secondary private IPs: can be assigned/unassigned
- Used for communication within VPC

#### Public IP Addresses
- Assigned from Amazon's public IP pool
- Dynamic: changes when instance stops/starts
- Released when instance terminates
- Only for instances in public subnets

#### Elastic IP Addresses
- Static public IP addresses
- Persist independent of instance lifecycle
- Can be moved between instances
- Charged when not associated with running instance

### Security Groups
#### Characteristics
- Virtual firewall for instances
- Stateful: return traffic automatically allowed
- Allow rules only (no deny rules)
- Evaluate all rules before deciding to allow traffic

#### Rules
- Protocol: TCP, UDP, ICMP, or protocol number
- Port range: single port or range
- Source/Destination: IP addresses, CIDR blocks, security groups
- Separate inbound and outbound rules

#### Best Practices
- Use descriptive names and descriptions
- Follow principle of least privilege
- Regularly review and audit rules
- Use referenced security groups when possible

### Network Access Control Lists (NACLs)
#### Characteristics
- Subnet-level firewall
- Stateless: return traffic must be explicitly allowed
- Allow and deny rules
- Rules processed in numerical order

#### Rules
- Rule number: 1-32766
- Protocol and port range
- Source/destination CIDR
- Allow or deny action

#### Default NACL
- Allows all inbound and outbound traffic
- Can be modified but not deleted
- Subnets associated by default

#### Custom NACLs
- Deny all traffic by default
- Must explicitly allow traffic
- Can be associated with multiple subnets

### VPC Peering
#### Overview
- Network connection between two VPCs
- Route traffic using private IP addresses
- VPCs appear to be on same network
- Can span regions and accounts

#### Limitations
- No transitive peering
- No overlapping CIDR blocks
- No edge-to-edge routing through gateways
- Limited to certain bandwidth

#### Use Cases
- Connect VPCs in same region
- Cross-region connectivity
- Cross-account resource sharing
- Centralized services access

### VPC Endpoints
#### Interface Endpoints (VPC Endpoint)
- Elastic Network Interface (ENI) with private IP
- Powered by AWS PrivateLink
- Connect to services over private network
- Supports many AWS services

#### Gateway Endpoints
- Gateway target for route table
- Only for S3 and DynamoDB
- No additional charges
- Highly available by design

#### Benefits
- Private connectivity to AWS services
- No internet gateway required
- Reduce data transfer costs
- Enhanced security posture

### Transit Gateway
#### Overview
- Regional network transit hub
- Connect VPCs and on-premises networks
- Simplify network topology
- Centralized connectivity management

#### Features
- Route tables for traffic control
- Multicast support
- Inter-region peering
- Direct Connect integration

#### Use Cases
- Hub-and-spoke network topology
- Centralized connectivity
- Network segmentation
- Simplified routing

### AWS PrivateLink
#### Overview
- Privately access services across VPC boundaries
- No internet gateway or NAT required
- Traffic doesn't leave Amazon network
- Powered by interface endpoints

#### Components
- VPC Endpoint Service: service provider side
- VPC Endpoint: service consumer side
- Network Load Balancer: distribute traffic
- Elastic Network Interface: endpoint interface

#### Benefits
- Enhanced security and compliance
- Reduced complexity
- Improved performance
- Network isolation

## Route 53

### Overview
- Highly available and scalable DNS service
- Domain registration service
- Health checking and failover
- Traffic routing policies

### DNS Record Types
#### A Record
- Maps domain to IPv4 address
- Most common record type
- Can have multiple values for load balancing

#### AAAA Record
- Maps domain to IPv6 address
- IPv6 equivalent of A record
- Growing importance with IPv6 adoption

#### CNAME Record
- Maps domain to another domain
- Cannot be used for root domain
- Useful for subdomains and aliases

#### MX Record
- Mail exchange record
- Specifies mail servers for domain
- Priority values for multiple servers

#### TXT Record
- Text information for domain
- Used for verification and configuration
- SPF, DKIM records for email

#### SRV Record
- Service location record
- Specifies hostname and port
- Used for specific services

#### NS Record
- Name server record
- Specifies authoritative name servers
- Delegates subdomain authority

#### PTR Record
- Pointer record for reverse DNS
- Maps IP address to domain name
- Required for some services

### Hosted Zones
#### Public Hosted Zones
- DNS configuration for public domains
- Accessible from internet
- Route traffic from anywhere
- Standard DNS queries

#### Private Hosted Zones
- DNS configuration within VPC
- Only accessible from associated VPCs
- Private domain resolution
- Enhanced security

### Routing Policies
#### Simple Routing
- Single resource record
- No health checks
- All values returned to client
- Client chooses which to use

#### Weighted Routing
- Associate multiple resources with single DNS name
- Specify weight for each resource
- Traffic distributed based on weights
- Useful for A/B testing and gradual deployments

#### Latency-Based Routing
- Route based on lowest latency
- Measure latency between users and AWS regions
- Automatically choose best region
- Improve user experience globally

#### Failover Routing
- Active-passive failover configuration
- Primary and secondary resources
- Health checks determine failover
- Automatic failover when primary fails

#### Geolocation Routing
- Route based on geographic location
- Country or continent-level routing
- Localization and content customization
- Compliance with data sovereignty

#### Geoproximity Routing
- Route based on geographic proximity
- Bias to shift traffic between resources
- More granular than geolocation
- Traffic flow policies required

#### Multivalue Answer Routing
- Return multiple IP addresses
- Each with independent health check
- Similar to simple routing with health checks
- Client can choose healthy endpoint

### Health Checks
#### Types
- HTTP/HTTPS endpoint monitoring
- TCP port monitoring
- Calculated health checks
- CloudWatch alarm-based checks

#### Features
- Multiple global locations
- Configurable check intervals
- String matching for HTTP(S)
- SNS notifications for failures

#### Use Cases
- Automated failover
- Load balancing
- Application monitoring
- Multi-region health checking

### Traffic Flow
#### Overview
- Visual traffic policy editor
- Complex routing configurations
- Version control for policies
- Global traffic management

#### Features
- Drag-and-drop policy editor
- Geoproximity routing with bias
- Policy versioning and testing
- Visual traffic flow representation

### Resolver
#### Overview
- DNS service for hybrid cloud
- Forward DNS queries between VPC and on-premises
- Conditional forwarding rules
- Enhanced DNS functionality

#### Components
- Resolver endpoints (inbound/outbound)
- Forwarding rules
- DNS query logging
- Integration with on-premises DNS

## AWS Direct Connect

### Overview
- Dedicated network connection to AWS
- Bypass internet for AWS connectivity
- Consistent network performance
- Reduce bandwidth costs

### Connection Types
#### Dedicated Connections
- Physical Ethernet connection
- 1 Gbps, 10 Gbps, 100 Gbps capacity
- Single customer use
- AWS Direct Connect partners provide

#### Hosted Connections
- Connection hosted by AWS Partner
- 50 Mbps to 10 Gbps capacity
- Shared infrastructure
- Faster deployment

### Virtual Interfaces (VIFs)
#### Public VIF
- Access AWS public services
- Use public IP addresses
- Connect to S3, DynamoDB, etc.
- No internet access

#### Private VIF
- Access VPC resources
- Use private IP addresses
- Connect to EC2, RDS, etc.
- One VIF per VPC connection

#### Transit VIF
- Connect to Transit Gateway
- Access multiple VPCs
- Simplified connectivity
- Central routing point

### Direct Connect Gateway
#### Overview
- Globally available resource
- Connect multiple VPCs across regions
- Work with Virtual Private Gateways
- No transitive routing

#### Benefits
- Simplified connectivity
- Reduce number of BGP sessions
- Global reach from single location
- Centralized management

### High Availability
#### Multiple Connections
- Redundant connections for HA
- Different Direct Connect locations
- Automatic failover capability
- Load balancing across connections

#### Hybrid Connectivity
- Direct Connect + VPN backup
- Best of both worlds
- Cost optimization
- Enhanced reliability

### LAG (Link Aggregation Groups)
#### Overview
- Bundle multiple connections
- Increase bandwidth
- Load distribution
- Redundancy within single location

#### Features
- Up to 4 connections per LAG
- Same capacity connections required
- Active-active load balancing
- Automatic failover

## VPN Connections

### Site-to-Site VPN
#### Overview
- IPsec VPN connection between on-premises and AWS
- Connect entire networks
- Encrypted tunnel over internet
- Quick setup and deployment

#### Components
- Virtual Private Gateway (VGW): AWS side
- Customer Gateway (CGW): customer side
- VPN Connection: IPsec tunnels
- Route tables: traffic direction

#### Routing
##### Static Routing
- Manually define routes
- Simple configuration
- Limited scalability
- Predictable routing

##### Dynamic Routing (BGP)
- Border Gateway Protocol
- Automatic route advertisement
- Better scalability
- Route optimization

#### High Availability
- Two IPsec tunnels per connection
- Different Availability Zones
- Automatic failover
- 99.95% availability SLA

### Client VPN
#### Overview
- Managed client-based VPN service
- Individual user connectivity
- OpenVPN-based solution
- Fine-grained access control

#### Features
- Scalable to thousands of users
- Integration with Active Directory
- Multi-factor authentication
- Split tunneling support

#### Use Cases
- Remote worker access
- Temporary access
- Third-party access
- Mobile device connectivity

## CloudFront

### Overview
- Global content delivery network (CDN)
- Cache content at edge locations
- Reduce latency for global users
- Integrate with AWS services

### Edge Locations
#### Global Network
- 400+ edge locations worldwide
- Regional edge caches
- Reduce origin load
- Improve user experience

#### Content Caching
- Static content (images, videos, files)
- Dynamic content with TTL 0
- API responses
- Live streaming

### Origins
#### S3 Buckets
- Static website hosting
- Origin Access Identity (OAI)
- Origin Access Control (OAC)
- Restrict direct S3 access

#### Custom Origins
- Application Load Balancer
- EC2 instances
- On-premises servers
- Any HTTP server

### Distribution Types
#### Web Distribution
- Static and dynamic content
- HTTP and HTTPS
- Custom domains (CNAMEs)
- Multiple origins

#### RTMP Distribution (Deprecated)
- Real-Time Messaging Protocol
- Live streaming media
- Being phased out
- Use other streaming solutions

### Caching Behavior
#### Cache Headers
- Cache-Control headers
- Expires headers
- ETags for validation
- Custom TTL settings

#### Query String Parameters
- Forward all parameters
- Forward specific parameters
- Ignore parameters
- Cache based on parameters

#### Cookies and Headers
- Forward all or specific cookies
- Custom header forwarding
- User-Agent forwarding
- Geographic headers

### Security Features
#### SSL/TLS
- Dedicated IP SSL certificates
- Server Name Indication (SNI)
- Custom SSL certificates
- AWS Certificate Manager integration

#### Web Application Firewall (WAF)
- SQL injection protection
- Cross-site scripting protection
- Rate limiting
- Custom rules

#### Origin Access Identity/Control
- Restrict S3 bucket access
- CloudFront-only access
- Enhanced security model
- Principle of least privilege

#### Signed URLs and Cookies
- Restrict access to content
- Time-based access control
- IP-based restrictions
- Custom policy documents

### Performance Optimization
#### Compression
- Automatic GZIP compression
- Reduce transfer size
- Faster content delivery
- Lower bandwidth costs

#### HTTP/2 Support
- Multiplexed connections
- Server push capability
- Binary protocol
- Improved performance

#### Caching Strategies
- Appropriate TTL values
- Cache invalidation
- Origin shield
- Regional edge caches

## Elastic Load Balancing

### Application Load Balancer (ALB)
#### Features
- Layer 7 load balancing
- HTTP/HTTPS traffic
- WebSocket support
- Advanced routing rules

#### Routing
- Host-based routing
- Path-based routing
- Query string routing
- HTTP header routing

#### Target Types
- EC2 instances
- IP addresses
- Lambda functions
- Containers (ECS tasks)

#### Health Checks
- HTTP/HTTPS health checks
- Custom health check paths
- Configurable intervals
- Unhealthy threshold settings

### Network Load Balancer (NLB)
#### Features
- Layer 4 load balancing
- TCP/UDP/TLS traffic
- Ultra-high performance
- Static IP addresses

#### Performance
- Millions of requests per second
- Ultra-low latency
- Handle volatile traffic patterns
- Connection-based load balancing

#### Target Types
- EC2 instances
- IP addresses
- Application Load Balancer
- On-premises servers

#### Preserve Source IP
- Client IP preservation
- No modification of requests
- Direct server return
- Transparent to applications

### Gateway Load Balancer (GWLB)
#### Features
- Layer 3 Gateway + Layer 4 Load Balancing
- Deploy third-party appliances
- Transparent network gateway
- GENEVE protocol support

#### Use Cases
- Security appliances
- Network appliances
- Deep packet inspection
- Intrusion detection systems

### Classic Load Balancer (CLB)
#### Features
- Legacy load balancer
- Layer 4 and basic Layer 7
- Limited advanced features
- Being phased out

#### Migration
- Migrate to ALB for HTTP/HTTPS
- Migrate to NLB for TCP/UDP
- Better features and performance
- Cost optimization

## API Gateway

### Overview
- Fully managed API gateway service
- Create, publish, and manage APIs
- Handle all API lifecycle
- Scale to millions of requests

### API Types
#### REST API
- RESTful API development
- Resource-based model
- Multiple deployment stages
- Comprehensive feature set

#### HTTP API
- Simpler, faster, and cheaper
- OpenAPI 3.0 support
- JWT authorizers
- CORS support

#### WebSocket API
- Real-time communication
- Bidirectional communication
- Connection management
- Route-based message handling

### Integration Types
#### Lambda Integration
- Serverless backend functions
- Automatic scaling
- Pay per request
- Event-driven architecture

#### HTTP Integration
- Proxy to HTTP endpoints
- On-premises or cloud services
- Request/response transformation
- Custom backends

#### AWS Service Integration
- Direct integration with AWS services
- DynamoDB, S3, Kinesis, etc.
- No intermediate compute required
- Cost-effective architecture

#### Mock Integration
- Return static responses
- API prototyping
- Testing and development
- No backend required

### Authorization
#### IAM Authorization
- AWS IAM policies
- SigV4 signed requests
- Fine-grained permissions
- AWS SDK integration

#### Lambda Authorizers
- Custom authorization logic
- JWT token validation
- Third-party identity providers
- Flexible authorization

#### Cognito User Pools
- Built-in user management
- JWT token authentication
- Social identity providers
- Enterprise identity integration

#### API Keys
- Simple API key validation
- Usage plans and throttling
- Basic access control
- Not for authorization alone

### Throttling and Caching
#### Request Throttling
- Rate limiting per client
- Burst capacity handling
- Per-stage and per-method limits
- 429 Too Many Requests response

#### Response Caching
- Cache API responses
- Configurable TTL
- Cache key customization
- Performance improvement

### Monitoring and Logging
#### CloudWatch Integration
- API metrics and alarms
- Request/error rates
- Latency metrics
- Custom metrics

#### Access Logging
- Request/response logging
- Custom log formats
- CloudWatch Logs integration
- Debugging and auditing

#### X-Ray Tracing
- End-to-end request tracing
- Performance analysis
- Error debugging
- Service map visualization

## Key Exam Tips

### VPC Design Principles
#### IP Address Planning
- Plan CIDR blocks carefully
- Allow for future growth
- Avoid overlapping ranges
- Consider peering requirements

#### Subnet Strategy
- Public subnets for internet-facing resources
- Private subnets for internal resources
- Database subnets in multiple AZs
- Dedicated subnets for specific functions

#### High Availability
- Multi-AZ deployments
- Multiple subnets per tier
- Load balancer distribution
- Auto Scaling across AZs

### Security Best Practices
#### Defense in Depth
- Multiple layers of security
- Security groups + NACLs
- WAF + CloudFront
- VPC + PrivateLink

#### Principle of Least Privilege
- Minimal required permissions
- Regular access reviews
- Role-based access control
- Time-limited access

#### Network Segmentation
- Separate tiers in different subnets
- Private communication paths
- VPC endpoints for AWS services
- Transit Gateway for complex topologies

### Performance Optimization
#### CDN Strategy
- Cache static content at edge
- Optimize cache policies
- Use appropriate origins
- Monitor cache hit ratios

#### Load Balancing
- Choose appropriate load balancer type
- Configure health checks properly
- Distribute across multiple AZs
- Monitor target health

#### DNS Optimization
- Use appropriate routing policies
- Implement health checks
- Optimize TTL values
- Consider latency-based routing

### Cost Optimization
#### Data Transfer Costs
- Use VPC endpoints
- Implement CloudFront
- Optimize Direct Connect usage
- Monitor data transfer patterns

#### Load Balancer Optimization
- Right-size load balancer capacity
- Use appropriate load balancer type
- Monitor utilization metrics
- Clean up unused resources

#### Network Resource Management
- Release unused Elastic IPs
- Optimize NAT Gateway usage
- Review VPC endpoint costs
- Monitor bandwidth utilization
