# AWS Storage Services

## Amazon S3 (Simple Storage Service)

### Overview
- Object storage service
- Virtually unlimited storage capacity
- 99.999999999% (11 9's) durability
- Highly scalable and available

### Storage Classes
#### S3 Standard
- Frequently accessed data
- Low latency and high throughput
- 99.99% availability
- Most expensive per GB

#### S3 Standard-IA (Infrequent Access)
- Long-term storage with infrequent access
- Lower storage cost than Standard
- Retrieval fees apply
- 99.9% availability

#### S3 One Zone-IA
- Infrequent access data in single AZ
- 20% less cost than Standard-IA
- 99.5% availability
- Not resilient to AZ destruction

#### S3 Intelligent-Tiering
- Automatically moves data between tiers
- No retrieval fees
- Small monthly monitoring fee
- Optimizes costs automatically

#### S3 Glacier Instant Retrieval
- Archive data with instant retrieval
- Minimum 90-day storage duration
- Lower cost than Standard-IA

#### S3 Glacier Flexible Retrieval
- Archive data for long-term backup
- Retrieval times: minutes to hours
- Minimum 90-day storage duration

#### S3 Glacier Deep Archive
- Lowest cost storage class
- Retrieval time: 12-48 hours
- Minimum 180-day storage duration
- Digital preservation and compliance

### Key Features
#### Versioning
- Keep multiple versions of objects
- Protect against accidental deletion
- Can be suspended but not disabled
- Noncurrent versions can be lifecycle managed

#### Lifecycle Management
- Automatically transition objects between storage classes
- Delete objects after specified time
- Applies to current and noncurrent versions
- Rule-based with filters

#### Cross-Region Replication (CRR)
- Replicate objects to different AWS regions
- Automatic replication of new objects
- Requires versioning enabled
- Use cases: compliance, latency reduction

#### Same-Region Replication (SRR)
- Replicate objects within same region
- Different storage classes or accounts
- Log aggregation and compliance

### Security Features
#### Bucket Policies
- JSON-based access policies
- Resource-based permissions
- Support for conditions and principals
- Can grant cross-account access

#### Access Control Lists (ACLs)
- Legacy access control mechanism
- Object and bucket level permissions
- Limited compared to bucket policies
- Consider disabling for security

#### Server-Side Encryption
- **SSE-S3**: AWS managed keys
- **SSE-KMS**: AWS KMS managed keys
- **SSE-C**: Customer provided keys
- **DSSE-KMS**: Dual layer encryption

#### Client-Side Encryption
- Encrypt data before uploading
- Manage encryption keys
- AWS SDKs provide libraries

### Performance Optimization
#### Request Rate Performance
- 3,500 PUT/COPY/POST/DELETE requests per second per prefix
- 5,500 GET/HEAD requests per second per prefix
- Scale by using different prefixes

#### Transfer Acceleration
- Uses CloudFront edge locations
- Faster uploads to S3
- Additional cost applies

#### Multipart Upload
- Upload large objects in parts
- Improved performance and reliability
- Recommended for objects > 100 MB
- Required for objects > 5 GB

#### S3 Select
- Retrieve subset of data from objects
- Reduce data transfer costs
- SQL-like queries on objects
- Works with CSV, JSON, Parquet

### Event Notifications
#### Supported Events
- Object creation and deletion
- Object restoration from Glacier
- Replication events

#### Destinations
- SNS topics
- SQS queues
- Lambda functions
- EventBridge

### Static Website Hosting
#### Configuration
- Enable static website hosting
- Specify index and error documents
- Configure routing rules

#### Custom Domain
- Use Route 53 for custom domain
- Bucket name must match domain name
- SSL/TLS via CloudFront

### S3 Transfer Family
#### SFTP, FTPS, FTP
- Managed file transfer protocols
- Scale to thousands of users
- Integration with existing workflows

## Amazon EBS (Elastic Block Store)

### Overview
- Block-level storage for EC2 instances
- Persistent storage independent of instance lifecycle
- Automatically replicated within AZ
- Can be attached to only one instance at a time

### Volume Types
#### General Purpose SSD (gp3)
- Latest generation general purpose SSD
- Baseline: 3,000 IOPS and 125 MiB/s
- Can provision up to 16,000 IOPS and 1,000 MiB/s
- Most cost-effective SSD option

#### General Purpose SSD (gp2)
- Previous generation general purpose SSD
- Burst performance with credits
- 3 IOPS per GB (minimum 100 IOPS)
- Burst up to 3,000 IOPS

#### Provisioned IOPS SSD (io2)
- High performance SSD for critical workloads
- Up to 64,000 IOPS per volume
- 99.999% durability
- Multi-Attach support

#### Provisioned IOPS SSD (io1)
- Previous generation high performance SSD
- Up to 64,000 IOPS per volume
- 99.999% durability
- Legacy option

#### Throughput Optimized HDD (st1)
- Low-cost HDD for frequently accessed workloads
- Baseline: 40 MiB/s per TB
- Burst up to 250 MiB/s per TB
- Cannot be boot volume

#### Cold HDD (sc1)
- Lowest cost HDD for infrequently accessed workloads
- Baseline: 12 MiB/s per TB
- Burst up to 80 MiB/s per TB
- Cannot be boot volume

### EBS Features
#### Snapshots
- Point-in-time backups to S3
- Incremental backups
- Can create volumes from snapshots
- Cross-region and cross-account copying

#### Encryption
- Encryption at rest and in transit
- Uses AWS KMS keys
- Minimal performance impact
- Can encrypt existing volumes via snapshots

#### Multi-Attach
- Attach single io1/io2 volume to multiple instances
- All instances in same AZ
- Requires cluster-aware file system
- Use case: shared storage for HA applications

#### Elastic Volumes
- Modify volume size, type, and IOPS
- No downtime required
- May require OS-level changes
- Cannot decrease volume size

### Backup and Recovery
#### EBS Snapshots
- Stored in S3 but not visible
- Incremental backups
- Can be automated with lifecycle policies
- Cross-region disaster recovery

#### AWS Backup
- Centralized backup across AWS services
- Backup policies and schedules
- Point-in-time recovery
- Cross-region and cross-account backups

### Performance Optimization
#### EBS-Optimized Instances
- Dedicated bandwidth for EBS traffic
- Prevents network contention
- Default on most modern instance types

#### Instance Store vs EBS
- Instance store: temporary, high IOPS
- EBS: persistent, network-attached
- Choose based on durability requirements

## Amazon EFS (Elastic File System)

### Overview
- Fully managed NFS file system
- Scalable and elastic
- Concurrent access from multiple instances
- POSIX-compliant file system

### Performance Modes
#### General Purpose
- Lower latency per operation
- Up to 7,000 file operations per second
- Default mode for most use cases

#### Max I/O
- Higher levels of aggregate throughput
- Higher latency per operation
- Scale to thousands of instances

### Throughput Modes
#### Bursting Throughput
- Throughput scales with file system size
- Burst credits for higher throughput
- Default mode

#### Provisioned Throughput
- Specify throughput independent of size
- Additional cost for provisioned capacity
- Predictable performance

### Storage Classes
#### Standard
- Frequently accessed files
- Stored across multiple AZs
- Highest durability and availability

#### Infrequent Access (IA)
- Lower cost for infrequently accessed files
- Lifecycle management policies
- Retrieval fees apply

### EFS Intelligent Tiering
- Automatically moves files to IA
- Based on access patterns
- No retrieval fees
- Cost optimization

### Access Points
- Application-specific entry points
- Enforce user identity and path
- POSIX permissions
- Simplify access management

### Backup
#### AWS Backup Integration
- Point-in-time recovery
- Cross-region backup
- Backup policies and schedules

#### EFS-to-EFS Backup
- Regional backup solution
- Incremental backups
- Restore to any EFS file system

### Use Cases
#### Content Management
- Web serving and content distribution
- Media processing workflows
- Shared application data

#### Big Data Analytics
- Parallel workloads
- Data lakes and analytics
- Machine learning training data

## Amazon FSx

### Overview
- Fully managed file systems
- Built on latest technologies
- Optimized for specific use cases
- High performance and feature-rich

### FSx for Windows File Server
#### Features
- Fully managed Windows file system
- SMB protocol support
- Active Directory integration
- Windows-native features (ACLs, shadow copies)

#### Performance
- SSD and HDD storage options
- Up to 2 GB/s throughput
- Sub-millisecond latencies
- Configurable throughput capacity

#### Use Cases
- Enterprise applications
- Home directories
- Content distribution
- Media processing

### FSx for Lustre
#### Features
- High-performance file system
- Optimized for compute-intensive workloads
- Integration with S3
- POSIX-compliant

#### Performance
- Hundreds of GB/s throughput
- Sub-millisecond latencies
- Millions of IOPS
- Linear scaling

#### Use Cases
- High Performance Computing (HPC)
- Machine learning
- Video processing
- Financial modeling

#### Deployment Options
- **Scratch**: Temporary storage, higher performance
- **Persistent**: Long-term storage, automatic backups

### FSx for NetApp ONTAP
#### Features
- Fully managed NetApp ONTAP
- NFS, SMB, iSCSI protocols
- Data deduplication and compression
- SnapMirror replication

#### Use Cases
- Enterprise file shares
- Database storage
- DevOps workflows
- Content distribution

### FSx for OpenZFS
#### Features
- Fully managed OpenZFS
- NFS protocol support
- Point-in-time snapshots
- Data compression

#### Performance
- Up to 12.5 GB/s throughput
- Up to 1 million IOPS
- Low latency storage

#### Use Cases
- Database backups
- DevOps workflows
- Content distribution
- Media processing

## AWS Storage Gateway

### Overview
- Hybrid cloud storage service
- Connect on-premises environments to AWS
- Seamless integration with AWS storage services
- Virtual machine or hardware appliance

### Gateway Types
#### File Gateway
- NFS and SMB file shares
- Files stored as objects in S3
- Local cache for frequently accessed data
- Use case: File shares, content distribution

#### Volume Gateway
##### Stored Volumes
- Primary data on-premises
- Asynchronous backup to S3 as EBS snapshots
- 1 GB to 16 TB per volume
- Use case: Backup existing infrastructure

##### Cached Volumes
- Primary data in S3
- Frequently accessed data cached locally
- 1 GB to 32 TB per volume
- Use case: Extend on-premises storage

#### Tape Gateway (VTL)
- Virtual Tape Library interface
- Virtual tapes stored in S3 and archived in Glacier
- Replace physical tape infrastructure
- Use case: Backup applications using tape

### Benefits
#### Cost Reduction
- Reduce on-premises storage costs
- Pay only for storage used in AWS
- Eliminate tape infrastructure costs

#### Scalability
- Scale storage capacity as needed
- No hardware procurement delays
- Automatic scaling in the cloud

#### Durability
- 99.999999999% (11 9's) durability
- Multiple copies in multiple facilities
- Automated backup and archival

### Architecture Considerations
#### Network Performance
- Adequate bandwidth for data transfer
- Consider AWS Direct Connect
- Local cache for performance

#### Security
- Encryption in transit and at rest
- VPC endpoints for private connectivity
- IAM for access control

## AWS DataSync

### Overview
- Data transfer service
- Move large amounts of data to AWS
- One-time migration or regular sync
- Network optimization and monitoring

### Features
#### High Performance
- Transfer speeds up to 10x faster than open-source tools
- Automatic network optimization
- Parallel transfer and compression

#### Security
- Encryption in transit (TLS)
- Data integrity verification
- IAM for access control

#### Scheduling
- One-time or recurring transfers
- CloudWatch Events integration
- Transfer only changed data

### Use Cases
#### Data Migration
- Move on-premises data to AWS
- Database migrations
- Archive cold data

#### Data Synchronization
- Keep data in sync between locations
- Disaster recovery
- Content distribution

### Supported Locations
#### Source
- On-premises NFS/SMB shares
- Self-managed object storage
- Amazon S3
- Amazon EFS
- Amazon FSx

#### Destination
- Amazon S3
- Amazon EFS
- Amazon FSx

## Key Exam Tips

### Storage Decision Framework
#### S3 Use Cases
- Static websites
- Data archiving
- Data lakes
- Content distribution
- Backup and restore

#### EBS Use Cases
- Boot volumes
- Database storage
- File systems
- Application data
- Snapshot backups

#### EFS Use Cases
- Shared file storage
- Content management
- Web serving
- Big data analytics
- Parallel workloads

#### FSx Use Cases
- Windows-based applications
- High-performance computing
- Machine learning
- Enterprise file shares

### Cost Optimization
#### S3 Storage Classes
- Use Intelligent-Tiering for unknown access patterns
- Lifecycle policies for predictable patterns
- Monitor access patterns with Storage Class Analysis

#### EBS Optimization
- Right-size volumes based on usage
- Use gp3 instead of gp2 for new workloads
- Delete unused snapshots and volumes

#### Data Transfer
- Use VPC endpoints to avoid data transfer charges
- Consider Direct Connect for large data transfers
- Use CloudFront for content distribution

### Security Best Practices
#### S3 Security
- Block public access by default
- Use bucket policies and IAM policies
- Enable server-side encryption
- Monitor access with CloudTrail

#### EBS Security
- Encrypt volumes with KMS
- Secure snapshots with appropriate permissions
- Use IAM roles for EC2 instances

#### Data in Transit
- Use SSL/TLS for all data transfers
- VPC endpoints for private connectivity
- AWS PrivateLink for service access

### Performance Considerations
#### S3 Performance
- Use appropriate prefixes for high request rates
- Implement multipart upload for large objects
- Use Transfer Acceleration for global users

#### EBS Performance
- Choose appropriate volume type for workload
- Use EBS-optimized instances
- Monitor IOPS and throughput metrics

#### Network Performance
- Consider placement groups for low latency
- Use enhanced networking features
- Monitor network utilization
