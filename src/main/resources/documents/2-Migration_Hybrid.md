# AWS Migration and Hybrid Cloud Services

## AWS Direct Connect

### Overview
- Dedicated network connection to AWS
- Consistent network performance
- Reduce bandwidth costs
- Hybrid cloud connectivity

### Direct Connect Components
#### Direct Connect Location
- Third-party data centers
- AWS Direct Connect partners
- Physical connection point
- Global presence

#### Cross Connect
- Physical connection between customer and AWS
- Ethernet connection
- Partner or customer managed
- Installation coordination

#### Virtual Interfaces (VIFs)
- Logical connections over physical link
- VLAN-based segmentation
- BGP routing protocol
- Multiple VIFs per connection

### Connection Types
#### Dedicated Connections
- Physical Ethernet port dedicated to customer
- 1 Gbps, 10 Gbps, 100 Gbps
- Direct to AWS equipment
- Customer or partner installation

#### Hosted Connections
- Shared physical connection
- 50 Mbps to 10 Gbps
- AWS Partner provides
- Faster provisioning

### Virtual Interface Types
#### Private VIF
- Connect to VPC resources
- Use private IP addresses
- Access EC2, RDS, etc.
- BGP routing required

#### Public VIF
- Connect to AWS public services
- Use public IP addresses
- Access S3, DynamoDB, etc.
- No internet access

#### Transit VIF
- Connect to AWS Transit Gateway
- Multiple VPC connectivity
- Simplified routing
- Centralized connectivity

### Direct Connect Gateway
#### Features
- Connect to multiple VPCs across regions
- Centralized Direct Connect management
- No transitive routing
- Simplified architecture

#### Benefits
- Multi-region connectivity
- Reduced complexity
- Fewer BGP sessions
- Centralized routing

### Link Aggregation Groups (LAG)
#### Features
- Bundle multiple connections
- Increase bandwidth
- Improve redundancy
- Active-active configuration

#### Requirements
- Same bandwidth connections
- Same Direct Connect location
- Maximum 4 connections
- Automatic failover

### High Availability
#### Redundancy Options
- Multiple Direct Connect locations
- Multiple connections per location
- Different carriers/providers
- Geographic diversity

#### Backup Connectivity
- VPN as backup
- Multiple Direct Connect connections
- Active-passive or active-active
- Automatic failover

### Direct Connect + VPN
#### Features
- IPsec VPN over Direct Connect
- Encrypted connection
- Compliance requirements
- Enhanced security

#### Use Cases
- Regulatory compliance
- Additional security layer
- Encrypted data transmission
- Policy requirements

### Routing and BGP
#### Border Gateway Protocol (BGP)
- Dynamic routing protocol
- AS (Autonomous System) numbers
- Route advertisement
- Path selection

#### Routing Policies
- Inbound and outbound filters
- AS path prepending
- Local preference
- Community attributes

### Security Features
#### Network Security
- Private connection to AWS
- No internet routing
- Dedicated bandwidth
- Physical security

#### Encryption Options
- Application-level encryption
- VPN over Direct Connect
- MACsec encryption
- End-to-end security

### Monitoring and Troubleshooting
#### CloudWatch Metrics
- Connection state
- BGP state
- Virtual interface state
- Data transfer metrics

#### Troubleshooting Tools
- BGP route tables
- Connection logs
- Network analysis
- AWS support assistance

## AWS Site-to-Site VPN

### Overview
- IPsec VPN connection over internet
- Connect on-premises to AWS
- Encrypted tunnels
- Quick deployment

### VPN Components
#### Virtual Private Gateway (VGW)
- AWS side of VPN connection
- Attached to VPC
- Route propagation
- High availability

#### Customer Gateway (CGW)
- Customer side configuration
- Public IP address
- BGP ASN (optional)
- Device configuration

#### VPN Connection
- IPsec tunnels
- Redundant tunnels
- Encryption protocols
- Authentication methods

### VPN Connection Types
#### Static Routing
- Manual route configuration
- Simple setup
- Limited scalability
- Predictable routing

#### Dynamic Routing (BGP)
- Automatic route advertisement
- Better scalability
- Route optimization
- Failover capabilities

### Tunnel Configuration
#### IPsec Parameters
- IKE (Internet Key Exchange)
- ESP (Encapsulating Security Payload)
- AES encryption
- SHA authentication

#### Tunnel Redundancy
- Two tunnels per connection
- Different Availability Zones
- Active-passive or active-active
- Automatic failover

### Transit Gateway VPN
#### Features
- VPN to Transit Gateway
- Multiple VPC connectivity
- Simplified routing
- Centralized VPN management

#### Benefits
- Scalable VPN connectivity
- Reduced complexity
- Centralized management
- Cost optimization

### Accelerated Site-to-Site VPN
#### Features
- Global Accelerator integration
- Improved performance
- Consistent routing
- Better reliability

#### Use Cases
- Global connectivity
- Performance optimization
- Consistent experience
- Network optimization

### Client VPN
#### Overview
- Managed client-based VPN
- Individual user connectivity
- OpenVPN protocol
- Scalable solution

#### Features
- Central authentication
- Fine-grained access control
- Multi-factor authentication
- Split tunneling

#### Authentication Methods
- Active Directory
- Mutual certificate
- SAML federation
- Custom authentication

### Monitoring and Logging
#### VPN Connection Monitoring
- Tunnel state
- BGP status
- Connection logs
- CloudWatch metrics

#### Flow Logs
- VPC Flow Logs
- Network traffic analysis
- Security monitoring
- Troubleshooting

## AWS Transit Gateway

### Overview
- Regional network transit hub
- Connect VPCs and on-premises networks
- Simplify network topology
- Centralized routing

### Transit Gateway Features
#### Hub-and-Spoke Architecture
- Central connectivity point
- Simplified routing
- Reduced complexity
- Scalable design

#### Cross-Region Peering
- Connect Transit Gateways across regions
- Global network connectivity
- Reduced latency routing
- Bandwidth optimization

#### Multicast Support
- IP multicast routing
- One-to-many communication
- Efficient bandwidth usage
- Application optimization

### Route Tables
#### Route Table Association
- Associate with VPC attachments
- Control traffic routing
- Segmentation capability
- Policy enforcement

#### Route Propagation
- Automatic route learning
- Dynamic routing updates
- BGP integration
- Simplified management

#### Route Filters
- Control route advertisement
- Security policies
- Network segmentation
- Traffic control

### Attachments
#### VPC Attachments
- Connect VPCs to Transit Gateway
- Subnet association
- Route table configuration
- Security group rules

#### VPN Attachments
- Site-to-Site VPN connections
- Dynamic or static routing
- Multiple tunnel support
- Failover capabilities

#### Direct Connect Attachments
- Direct Connect Gateway integration
- High bandwidth connectivity
- Consistent performance
- Hybrid cloud architecture

#### Peering Attachments
- Cross-region connectivity
- Transit Gateway peering
- Global network design
- Inter-region routing

### Network Segmentation
#### Route Table Isolation
- Separate route tables
- Network segmentation
- Security boundaries
- Policy enforcement

#### Security Groups
- Instance-level security
- Application protection
- Microsegmentation
- Defense in depth

### Monitoring and Logging
#### Flow Logs
- VPC Flow Logs support
- Network traffic analysis
- Security monitoring
- Compliance reporting

#### CloudWatch Metrics
- Attachment state
- Route table metrics
- Data transfer statistics
- Performance monitoring

## AWS Storage Gateway

### Overview
- Hybrid cloud storage service
- Connect on-premises to AWS storage
- Seamless integration
- Multiple deployment options

### Gateway Types
#### File Gateway
- NFS and SMB file shares
- Store files as S3 objects
- Local cache for performance
- File share protocols

##### Features
- POSIX file system interface
- Local cache for frequently accessed data
- Asynchronous upload to S3
- Integration with S3 features

##### Use Cases
- File share replacement
- Content distribution
- Data lakes
- Archive to cloud

#### Volume Gateway
##### Stored Volumes
- Primary data on-premises
- Asynchronous backup to S3
- 1 GB to 16 TB volumes
- Point-in-time snapshots

##### Cached Volumes
- Primary data in S3
- Local cache for frequent access
- 1 GB to 32 TB volumes
- Lower local storage requirements

##### Use Cases
- Backup to cloud
- Disaster recovery
- Data archival
- Storage optimization

#### Tape Gateway (VTL)
- Virtual Tape Library interface
- Replace physical tape infrastructure
- Virtual tapes in S3 and Glacier
- Existing backup software integration

##### Features
- Virtual Tape Library (VTL)
- Virtual Tape Shelf (VTS)
- Media changer support
- Barcode support

##### Use Cases
- Tape replacement
- Backup modernization
- Archive to cloud
- Compliance requirements

### Deployment Options
#### VM-based Deployment
- VMware vSphere
- Microsoft Hyper-V
- Linux KVM
- On-premises deployment

#### Hardware Appliance
- Pre-configured hardware
- Dell hardware platform
- Simplified deployment
- Support included

#### EC2 Instance
- Cloud-based deployment
- Testing and development
- Migration scenarios
- Temporary use cases

### Storage Gateway Architecture
#### Local Storage
- Cache storage
- Upload buffer
- Working storage
- Performance optimization

#### Cloud Storage
- S3 bucket storage
- Glacier archival
- Cross-region replication
- Lifecycle policies

#### Network Connectivity
- Internet connectivity
- VPC endpoint support
- Direct Connect integration
- Bandwidth optimization

### Security Features
#### Encryption
- Encryption in transit
- Encryption at rest
- Key management
- Data protection

#### Access Control
- IAM integration
- Bucket policies
- File system permissions
- Authentication methods

### Monitoring and Management
#### CloudWatch Integration
- Performance metrics
- Alarm configuration
- Operational monitoring
- Capacity planning

#### AWS Storage Gateway Console
- Gateway management
- Configuration updates
- Status monitoring
- Maintenance windows

## AWS Database Migration Service (DMS)

### Overview
- Migrate databases to AWS
- Homogeneous and heterogeneous migrations
- Minimal downtime
- Continuous data replication

### Migration Types
#### Homogeneous Migrations
- Same database engine
- Simpler migration process
- Oracle to Oracle RDS
- MySQL to MySQL RDS

#### Heterogeneous Migrations
- Different database engines
- Schema conversion required
- Oracle to PostgreSQL
- SQL Server to MySQL

### DMS Components
#### Replication Instance
- EC2 instance running DMS
- Performs migration tasks
- Multi-AZ deployment option
- Instance class selection

#### Source Endpoints
- Source database configuration
- Connection parameters
- Authentication settings
- SSL configuration

#### Target Endpoints
- Target database configuration
- AWS or on-premises
- Connection parameters
- Load settings

#### Migration Tasks
- Define migration workflow
- Table mapping rules
- Transformation rules
- Migration type selection

### Migration Task Types
#### Full Load
- One-time data migration
- Historical data transfer
- Point-in-time snapshot
- Initial data migration

#### Full Load + CDC
- Initial full load
- Continuous replication
- Minimal downtime
- Real-time synchronization

#### CDC Only
- Change data capture only
- Ongoing replication
- Requires initial sync
- Data warehouse scenarios

### AWS Schema Conversion Tool (SCT)
#### Features
- Convert database schemas
- Assess migration complexity
- Generate assessment reports
- Application code conversion

#### Supported Conversions
- Oracle to PostgreSQL/MySQL
- SQL Server to PostgreSQL/MySQL
- Data warehouse migrations
- Stored procedure conversion

#### Assessment Reports
- Conversion complexity analysis
- Manual intervention requirements
- Estimated effort
- Migration recommendations

### DMS Fleet Advisor
#### Features
- Discover source database inventory
- Collect performance metrics
- Generate migration recommendations
- Target platform suggestions

#### Discovery Process
- Automated inventory collection
- Performance data analysis
- Capacity planning
- Right-sizing recommendations

### Monitoring and Troubleshooting
#### CloudWatch Integration
- Task metrics
- Replication performance
- Error monitoring
- Operational insights

#### Task Logs
- Detailed task execution logs
- Error message analysis
- Troubleshooting information
- Performance analysis

### Security Features
#### Network Security
- VPC deployment
- Security groups
- Subnet configuration
- Private connectivity

#### Encryption
- SSL/TLS in transit
- Encryption at rest
- Key management
- Secure replication

## AWS Application Discovery Service

### Overview
- Discover on-premises applications
- Plan migration to AWS
- Dependency mapping
- Right-sizing recommendations

### Discovery Methods
#### Agentless Discovery
- VMware vCenter integration
- VM configuration data
- Performance metrics
- Network dependencies

#### Agent-based Discovery
- Detailed system information
- Application dependencies
- Network connections
- Process information

### Data Collection
#### System Information
- Hardware specifications
- Operating system details
- Software inventory
- Configuration data

#### Performance Metrics
- CPU utilization
- Memory usage
- Disk I/O
- Network throughput

#### Dependencies
- Application connections
- Database dependencies
- Service relationships
- Communication patterns

### Integration
#### AWS Migration Hub
- Centralized migration tracking
- Progress monitoring
- Migration planning
- Status dashboard

#### Partner Tools
- Third-party migration tools
- Integration capabilities
- Extended functionality
- Specialized solutions

## AWS Server Migration Service (SMS)

### Overview (Deprecated)
- Legacy service for VM migration
- Automated server migration
- Incremental replication
- Minimal downtime

### Replacement Services
#### AWS Application Migration Service (MGN)
- Lift-and-shift migrations
- Block-level replication
- Automated conversion
- Minimal downtime

#### AWS Migration Hub
- Migration tracking
- Progress monitoring
- Centralized dashboard
- Integration with tools

## AWS Application Migration Service (MGN)

### Overview
- Lift-and-shift application migration
- Minimize business disruption
- Simplify migration process
- Automate infrastructure conversion

### Migration Process
#### Source Server Installation
- Install MGN agent
- Continuous data replication
- Block-level replication
- Real-time synchronization

#### Testing Phase
- Launch test instances
- Validate applications
- Performance testing
- Minimal disruption

#### Cutover Phase
- Final synchronization
- Production cutover
- DNS updates
- Business continuity

### Features
#### Continuous Data Replication
- Block-level replication
- Real-time synchronization
- Bandwidth optimization
- Network resilience

#### Automated Conversion
- Infrastructure adaptation
- EC2 instance creation
- Network configuration
- Storage optimization

#### Non-disruptive Testing
- Test without impacting source
- Multiple test iterations
- Validation capabilities
- Risk reduction

### Supported Platforms
#### Source Platforms
- Physical servers
- VMware vSphere
- Microsoft Hyper-V
- Cloud platforms

#### Target Platform
- Amazon EC2
- Various instance types
- Multiple operating systems
- Flexible configuration

## Key Exam Tips

### Connectivity Options
#### Direct Connect vs VPN
- Direct Connect: Dedicated, consistent performance, higher cost
- VPN: Over internet, variable performance, lower cost
- Hybrid: Use both for redundancy

#### Transit Gateway Use Cases
- Hub-and-spoke topology
- Multiple VPC connectivity
- On-premises integration
- Simplified routing

### Migration Strategy
#### Database Migration
- DMS for database migration
- SCT for schema conversion
- Consider migration types (full load, CDC)
- Test thoroughly before cutover

#### Application Migration
- Application Discovery Service for planning
- MGN for lift-and-shift
- Test phase before production
- Minimize business disruption

### Storage Gateway Selection
#### File Gateway
- NFS/SMB file shares
- File-based workloads
- S3 integration
- Content distribution

#### Volume Gateway
- Block storage requirements
- Backup and DR scenarios
- iSCSI protocol
- Snapshot capabilities

#### Tape Gateway
- Existing tape infrastructure
- VTL interface
- Backup software integration
- Archive requirements

### Hybrid Architecture Design
#### Network Connectivity
- Bandwidth requirements
- Latency considerations
- Redundancy needs
- Cost optimization

#### Data Classification
- Sensitive data on-premises
- Archive data in cloud
- Backup and DR strategy
- Compliance requirements

### Security Considerations
#### Network Security
- Private connectivity options
- Encryption in transit
- VPC security groups
- Network segmentation

#### Data Protection
- Encryption at rest
- Access controls
- Key management
- Audit logging

### Cost Optimization
#### Connectivity Costs
- Direct Connect vs internet
- Data transfer charges
- Bandwidth utilization
- Redundancy requirements

#### Migration Planning
- Right-sizing recommendations
- Phased migration approach
- Resource optimization
- Cost monitoring

### Migration Best Practices
#### Assessment Phase
- Discover all dependencies
- Performance baseline
- Compatibility analysis
- Risk assessment

#### Planning Phase
- Migration strategy
- Timeline development
- Resource allocation
- Testing approach

#### Execution Phase
- Pilot migrations
- Monitoring and validation
- Rollback procedures
- Documentation

#### Post-Migration
- Performance optimization
- Cost optimization
- Security hardening
- Operational procedures
