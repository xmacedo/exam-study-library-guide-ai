# AWS Database Services

## Amazon RDS (Relational Database Service)

### Overview
- Managed relational database service
- Automated backups, patching, and scaling
- Multi-AZ deployments for high availability
- Read replicas for read scaling

### Supported Database Engines
#### Amazon Aurora
- MySQL and PostgreSQL compatible
- Up to 5x faster than MySQL, 3x faster than PostgreSQL
- Auto-scaling storage (10GB to 128TB)
- Up to 15 read replicas

#### MySQL
- Popular open-source database
- Versions 5.7 and 8.0 supported
- Storage auto-scaling available
- Up to 5 read replicas

#### PostgreSQL
- Advanced open-source database
- Versions 11, 12, 13, 14, 15 supported
- JSON and advanced data types
- Up to 5 read replicas

#### MariaDB
- MySQL fork with additional features
- Versions 10.4, 10.5, 10.6 supported
- Compatible with MySQL applications
- Up to 5 read replicas

#### Oracle
- Enterprise database
- Standard and Enterprise editions
- Bring Your Own License (BYOL)
- License included options

#### SQL Server
- Microsoft database
- Express, Web, Standard, Enterprise editions
- Windows and Linux support
- License included options

### RDS Instance Classes
#### Burstable Performance (T3/T4g)
- Baseline CPU with burst capability
- Cost-effective for variable workloads
- CPU credits system

#### General Purpose (M5/M6i)
- Balanced compute, memory, and network
- Consistent performance
- Most workloads

#### Memory Optimized (R5/R6i/X1e/X2)
- High memory-to-vCPU ratio
- In-memory databases and analytics
- Real-time big data analytics

### Storage Types
#### General Purpose SSD (gp2)
- Baseline IOPS with burst capability
- 3 IOPS per GB (minimum 100)
- Cost-effective for most workloads

#### General Purpose SSD (gp3)
- Predictable baseline performance
- 3,000 IOPS and 125 MiB/s baseline
- Provision additional IOPS and throughput

#### Provisioned IOPS SSD (io1)
- High-performance SSD storage
- Up to 64,000 IOPS
- I/O-intensive workloads

#### Magnetic Storage
- Previous generation storage
- Lower cost but lower performance
- Not recommended for new deployments

### High Availability and Disaster Recovery
#### Multi-AZ Deployments
- Automatic failover to standby
- Synchronous replication
- No performance impact
- Automatic backups from standby

#### Read Replicas
- Asynchronous replication
- Read scaling and disaster recovery
- Cross-region support
- Can be promoted to standalone DB

#### Automated Backups
- Point-in-time recovery
- Retention period: 0-35 days
- Stored in S3
- No performance impact

#### Manual Snapshots
- User-initiated backups
- Retained until manually deleted
- Can be shared across accounts
- Cross-region copying

### Security Features
#### Network Security
- VPC for network isolation
- Security groups and NACLs
- Private subnets recommended
- VPC endpoints for API access

#### Encryption
- Encryption at rest with KMS
- Encryption in transit with SSL/TLS
- Encrypted backups and snapshots
- Transparent Data Encryption (TDE) for Oracle/SQL Server

#### Access Control
- IAM database authentication
- Database users and permissions
- Parameter groups for configuration
- Option groups for additional features

#### Monitoring and Auditing
- CloudWatch metrics and alarms
- Enhanced monitoring
- Performance Insights
- Database activity streams (Oracle/SQL Server)

### Performance Optimization
#### Performance Insights
- Database performance tuning
- Wait event analysis
- SQL statement analysis
- Historical performance data

#### Parameter Groups
- Database configuration parameters
- Custom parameter groups
- Apply changes with or without reboot

#### Connection Pooling
- RDS Proxy for connection management
- Improved application availability
- Reduced database load
- IAM authentication support

### Maintenance and Updates
#### Maintenance Windows
- Scheduled maintenance periods
- Minor version upgrades
- Security patches
- Can be customized

#### Blue/Green Deployments
- Zero-downtime deployments
- Create identical staging environment
- Switch traffic when ready
- Rollback capability

## Amazon Aurora

### Overview
- Cloud-native relational database
- MySQL and PostgreSQL compatible
- Distributed, fault-tolerant storage
- Up to 15 read replicas

### Architecture
#### Storage
- 6 copies across 3 AZs
- Self-healing with peer-to-peer replication
- Continuous backup to S3
- Auto-scaling from 10GB to 128TB

#### Compute
- Separate compute and storage
- Scale compute without affecting storage
- Multiple instance types available

### Aurora Serverless
#### Overview
- Automatically scales compute capacity
- Pay per second for actual usage
- No capacity planning required
- Ideal for unpredictable workloads

#### Use Cases
- Development and testing
- Infrequent workloads
- Variable workloads
- Multi-tenant applications

#### Aurora Serverless v2
- More granular scaling
- Supports read replicas
- Global Database support
- All Aurora features available

### Aurora Global Database
#### Features
- Cross-region read replicas
- Sub-second data replication
- Up to 5 secondary regions
- Disaster recovery and global reads

#### Use Cases
- Global applications
- Disaster recovery
- Read scaling across regions
- Compliance requirements

### Aurora Multi-Master
#### Features
- Multiple write nodes
- Continuous availability
- Conflict detection and resolution
- MySQL compatible only

#### Use Cases
- Applications requiring continuous writes
- Regional write scaling
- Zero-downtime maintenance

### Performance Features
#### Parallel Query
- Push query processing to storage layer
- Faster analytical queries
- Reduced network traffic
- MySQL compatible only

#### Fast Cloning
- Copy-on-write cloning
- Nearly instantaneous
- Independent database instances
- Development and testing

#### Backtrack
- "Rewind" database to previous point in time
- No backups required
- Fast recovery from user errors
- MySQL compatible only

## Amazon DynamoDB

### Overview
- Fully managed NoSQL database
- Single-digit millisecond latency
- Automatic scaling
- Built-in security and backup

### Data Model
#### Tables
- Collection of items
- No predefined schema
- Partition key required
- Optional sort key

#### Items
- Collection of attributes
- Maximum size: 400KB
- Flexible schema
- JSON document support

#### Attributes
- Name-value pairs
- Various data types supported
- Nested attributes allowed

### Primary Keys
#### Partition Key (Hash Key)
- Single attribute primary key
- Determines partition for item
- Must be unique across table

#### Composite Key (Partition + Sort Key)
- Two-attribute primary key
- Partition key + sort key combination
- Enables range queries

### Capacity Modes
#### On-Demand
- Pay per request
- No capacity planning
- Automatic scaling
- Higher cost per request

#### Provisioned
- Specify read and write capacity
- Auto Scaling available
- Reserved Capacity options
- Lower cost for predictable workloads

### Global Secondary Indexes (GSI)
#### Features
- Alternative query patterns
- Different partition and sort keys
- Eventually consistent reads
- Independent throughput settings

#### Best Practices
- Minimize number of GSIs
- Project only needed attributes
- Monitor throttling
- Consider sparse indexes

### Local Secondary Indexes (LSI)
#### Features
- Same partition key as table
- Different sort key
- Strongly consistent reads available
- Share throughput with table

#### Limitations
- Must be created at table creation
- Maximum 10 LSIs per table
- 10GB limit per partition key value

### DynamoDB Streams
#### Features
- Capture changes to table items
- 24-hour retention period
- Ordered by partition key
- Integration with Lambda

#### Stream View Types
- KEYS_ONLY: Only key attributes
- NEW_IMAGE: Entire item after change
- OLD_IMAGE: Entire item before change
- NEW_AND_OLD_IMAGES: Both images

### Global Tables
#### Features
- Multi-region, multi-master replication
- Eventually consistent across regions
- Active-active replication
- Conflict resolution

#### Use Cases
- Global applications
- Disaster recovery
- Regional data sovereignty
- Reduced latency

### DynamoDB Accelerator (DAX)
#### Features
- In-memory cache for DynamoDB
- Microsecond latency
- Transparent to applications
- Fully managed

#### Use Cases
- Read-heavy workloads
- Real-time applications
- Gaming leaderboards
- Ad serving

### Security Features
#### Encryption
- Encryption at rest with KMS
- Encryption in transit with TLS
- Client-side encryption libraries
- Backup encryption

#### Access Control
- IAM policies for API access
- Fine-grained access control
- VPC endpoints available
- Resource-based policies

#### Monitoring
- CloudWatch metrics
- CloudTrail for API calls
- DynamoDB Insights
- Contributor Insights

### Best Practices
#### Design Patterns
- Single table design
- Hierarchical data modeling
- Composite sort keys
- Sparse indexes

#### Performance
- Distribute requests across partitions
- Use burst capacity wisely
- Monitor hot partitions
- Optimize query patterns

#### Cost Optimization
- Use appropriate capacity mode
- Monitor unused indexes
- Implement data lifecycle policies
- Consider Reserved Capacity

## Amazon ElastiCache

### Overview
- Fully managed in-memory data store
- Sub-millisecond latency
- Redis and Memcached engines
- Automatic failover and backup

### ElastiCache for Redis
#### Features
- Advanced data structures
- Persistence options
- Replication and clustering
- Pub/Sub messaging

#### Cluster Modes
##### Cluster Mode Disabled
- Single primary node
- Up to 5 read replicas
- Automatic failover
- Manual scaling

##### Cluster Mode Enabled
- Multiple shards (partitions)
- Up to 500 nodes per cluster
- Automatic scaling
- Online resharding

#### Replication
- Asynchronous replication
- Multi-AZ deployments
- Automatic failover
- Read scaling

#### Backup and Restore
- Automatic backups
- Manual snapshots
- Point-in-time recovery
- Cross-region backup copying

### ElastiCache for Memcached
#### Features
- Simple key-value store
- Multi-threaded architecture
- No persistence
- Horizontal scaling

#### Scaling
- Add or remove nodes
- Auto Discovery for clients
- No data durability
- Partition tolerance

#### Use Cases
- Simple caching
- Session storage
- Database query results
- Computed data caching

### Security Features
#### Network Security
- VPC deployment
- Security groups
- Subnet groups
- Transit encryption

#### Authentication
- Redis AUTH
- IAM authentication (Redis 6.0+)
- User-based access control
- Role-based access control

#### Encryption
- Encryption at rest
- Encryption in transit
- Backup encryption
- KMS integration

### Monitoring and Maintenance
#### CloudWatch Metrics
- CPU utilization
- Memory usage
- Cache hit ratio
- Network throughput

#### Maintenance
- Automatic patching
- Maintenance windows
- Service updates
- Version upgrades

### Use Cases
#### Database Caching
- Reduce database load
- Improve response times
- Cache query results
- Session storage

#### Real-time Analytics
- Leaderboards
- Counting and statistics
- Geospatial indexing
- Machine learning

#### Gaming
- Session data
- Leaderboards
- Player profiles
- Real-time messaging

## Amazon Redshift

### Overview
- Fully managed data warehouse
- Petabyte-scale analytics
- Columnar storage
- MPP (Massively Parallel Processing)

### Architecture
#### Cluster Components
- Leader node: Query planning and coordination
- Compute nodes: Query execution and storage
- Node slices: Parallel processing units

#### Node Types
##### Dense Compute (dc2)
- SSD storage
- High performance
- Compute-intensive workloads

##### Dense Storage (ds2)
- HDD storage
- Large data warehouses
- Cost-effective storage

##### ra3 Nodes
- Managed storage
- Separate compute and storage scaling
- S3 integration for cold data

### Redshift Serverless
#### Features
- No infrastructure management
- Automatic scaling
- Pay for actual usage
- Data warehouse in minutes

#### Use Cases
- Variable workloads
- Development and testing
- Proof of concepts
- Unpredictable analytics

### Performance Features
#### Columnar Storage
- Efficient compression
- Reduced I/O
- Better query performance
- Analytical workloads

#### Massively Parallel Processing
- Distribute queries across nodes
- Parallel execution
- Linear scalability
- High throughput

#### Advanced Query Accelerator (AQUA)
- Hardware-accelerated cache
- Faster query performance
- Reduced compute node usage
- Automatic optimization

### Data Loading
#### COPY Command
- Bulk loading from S3
- Parallel loading
- Compression support
- Error handling

#### Redshift Spectrum
- Query data in S3
- No loading required
- Standard SQL queries
- Extend data warehouse

#### Data API
- Serverless access
- No persistent connections
- AWS SDK integration
- Event-driven processing

### Security Features
#### Network Security
- VPC deployment
- Security groups
- Enhanced VPC routing
- Private endpoints

#### Encryption
- Encryption at rest
- Encryption in transit
- Hardware Security Modules
- Key management

#### Access Control
- IAM integration
- Database users and roles
- Column-level security
- Row-level security

#### Auditing
- CloudTrail integration
- Database audit logs
- User activity logs
- Query monitoring

### Monitoring and Optimization
#### Performance Monitoring
- Query performance insights
- System tables and views
- CloudWatch metrics
- Advisor recommendations

#### Query Optimization
- Automatic query optimization
- Workload management
- Query queues
- Resource allocation

#### Maintenance
- Automatic maintenance
- Resize operations
- Backup and restore
- Snapshot management

## Database Migration Service (DMS)

### Overview
- Migrate databases to AWS
- Homogeneous and heterogeneous migrations
- Minimal downtime migrations
- Continuous data replication

### Migration Types
#### Homogeneous Migrations
- Same database engine
- Oracle to Oracle on RDS
- Simpler migration process
- Schema conversion not required

#### Heterogeneous Migrations
- Different database engines
- Oracle to PostgreSQL
- Requires schema conversion
- More complex process

#### Hybrid Migrations
- On-premises to cloud
- Cloud to on-premises
- Cloud to cloud
- Multi-cloud scenarios

### DMS Components
#### Replication Instance
- Compute resource for migration
- Runs replication software
- Connects source and target
- High availability options

#### Source Endpoints
- Database connection information
- Supported sources include:
  - Oracle, SQL Server, MySQL, PostgreSQL
  - MongoDB, S3, Kinesis
  - Azure SQL Database

#### Target Endpoints
- Destination database information
- Supported targets include:
  - All RDS engines
  - DynamoDB, Redshift
  - S3, Elasticsearch
  - Kinesis Data Streams

### Schema Conversion Tool (SCT)
#### Features
- Convert database schemas
- Application code conversion
- Assessment reports
- Optimization recommendations

#### Supported Conversions
- Oracle to PostgreSQL/MySQL
- SQL Server to PostgreSQL/MySQL
- Data warehouse migrations
- Application code migration

### Migration Tasks
#### Full Load
- Migrate existing data
- One-time migration
- Point-in-time snapshot
- No ongoing replication

#### Full Load + CDC
- Migrate existing data
- Continuous replication
- Minimal downtime
- Real-time synchronization

#### CDC Only
- Replicate ongoing changes
- Requires initial synchronization
- Real-time replication
- Data warehousing scenarios

### Best Practices
#### Planning
- Assess source databases
- Size replication instance appropriately
- Plan for network bandwidth
- Test migrations thoroughly

#### Performance
- Optimize source database
- Use Multi-AZ for production
- Monitor replication lag
- Tune replication settings

#### Security
- Encrypt connections
- Use IAM roles
- Secure credentials
- VPC deployment

## Key Exam Tips

### Database Selection Criteria
#### RDS Use Cases
- Traditional relational databases
- ACID transactions required
- Complex queries and joins
- Existing applications

#### Aurora Use Cases
- Cloud-native applications
- High performance requirements
- Global applications
- Variable workloads (Serverless)

#### DynamoDB Use Cases
- NoSQL applications
- Single-digit millisecond latency
- Massive scale requirements
- Serverless architectures

#### ElastiCache Use Cases
- In-memory caching
- Session storage
- Real-time analytics
- Performance optimization

#### Redshift Use Cases
- Data warehousing
- Business intelligence
- Analytical workloads
- Historical data analysis

### High Availability Patterns
#### Multi-AZ Deployments
- RDS Multi-AZ for failover
- Aurora automatic failover
- DynamoDB global tables
- ElastiCache Multi-AZ

#### Read Scaling
- RDS read replicas
- Aurora read replicas
- DynamoDB eventually consistent reads
- ElastiCache read replicas

#### Backup Strategies
- Automated backups
- Point-in-time recovery
- Cross-region backups
- Manual snapshots

### Performance Optimization
#### Database Performance
- Choose appropriate instance types
- Optimize queries and indexes
- Use connection pooling
- Monitor performance metrics

#### Caching Strategies
- Database query result caching
- Session data caching
- Application-level caching
- CDN for static content

#### Scaling Strategies
- Vertical scaling (larger instances)
- Horizontal scaling (read replicas)
- Partitioning and sharding
- Auto Scaling policies

### Security Best Practices
#### Network Security
- Deploy in private subnets
- Use security groups and NACLs
- VPC endpoints for AWS services
- Bastion hosts for access

#### Data Protection
- Encryption at rest and in transit
- Regular security updates
- Access logging and monitoring
- Backup encryption

#### Access Control
- Principle of least privilege
- IAM database authentication
- Regular access reviews
- Strong password policies

### Cost Optimization
#### Instance Right-Sizing
- Monitor CPU and memory usage
- Use appropriate instance families
- Consider burstable instances
- Regular performance reviews

#### Storage Optimization
- Choose appropriate storage types
- Monitor storage growth
- Implement data lifecycle policies
- Compress and archive old data

#### Reserved Capacity
- Use Reserved Instances for predictable workloads
- DynamoDB Reserved Capacity
- Redshift Reserved Nodes
- ElastiCache Reserved Nodes
