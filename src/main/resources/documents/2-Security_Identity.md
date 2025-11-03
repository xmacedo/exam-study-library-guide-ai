# AWS Security and Identity Services

## AWS Identity and Access Management (IAM)

### Overview
- Centralized access control for AWS resources
- Authentication and authorization service
- Global service (not region-specific)
- Fine-grained permissions management

### IAM Components
#### Users
- Individual identities for people or applications
- Long-term credentials (access keys, passwords)
- Can belong to multiple groups
- Direct policy attachment possible

#### Groups
- Collection of users
- Simplify permission management
- Cannot be nested (groups within groups)
- Users inherit group permissions

#### Roles
- Set of permissions without long-term credentials
- Assumable by users, applications, or AWS services
- Temporary security credentials (STS)
- Cross-account access capability

#### Policies
- JSON documents defining permissions
- Version-controlled documents
- Effect, Action, Resource, Condition elements
- Managed or inline policies

### Policy Types
#### AWS Managed Policies
- Created and maintained by AWS
- Cannot be modified by customers
- Cover common use cases
- Automatically updated by AWS

#### Customer Managed Policies
- Created and maintained by customers
- Version control and rollback
- Reusable across multiple entities
- More flexible than inline policies

#### Inline Policies
- Directly attached to single user, group, or role
- One-to-one relationship
- Deleted when entity is deleted
- Use for specific, unique permissions

### Policy Structure
#### Elements
- **Version**: Policy language version (2012-10-17)
- **Statement**: Array of permission statements
- **Effect**: Allow or Deny
- **Action**: API actions being allowed/denied
- **Resource**: AWS resources the actions apply to
- **Condition**: Optional conditions for when policy applies

#### Example Policy
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::mybucket/*",
      "Condition": {
        "StringEquals": {
          "s3:prefix": "home/${aws:username}/"
        }
      }
    }
  ]
}
```

### Permission Evaluation
#### Decision Process
1. By default, all requests are denied
2. Explicit allow overrides default deny
3. Explicit deny overrides any allow
4. Multiple policies are combined (union)

#### Policy Evaluation Logic
- Identity-based policies (attached to users, groups, roles)
- Resource-based policies (attached to resources)
- Permissions boundaries (maximum permissions)
- Service control policies (organizational restrictions)

### Cross-Account Access
#### Methods
- Cross-account roles (recommended)
- Resource-based policies
- Cross-account resource sharing
- Third-party access via external ID

#### Best Practices
- Use roles instead of sharing access keys
- Implement external ID for third-party access
- Principle of least privilege
- Regular access reviews

### Security Best Practices
#### Root Account Protection
- Enable MFA on root account
- Don't use root for daily activities
- Limit root account usage
- Monitor root account activities

#### User Management
- Individual users for each person
- Enable MFA for privileged users
- Rotate access keys regularly
- Use temporary credentials when possible

#### Access Key Management
- Don't embed in application code
- Use IAM roles for EC2 instances
- Rotate keys regularly
- Monitor key usage

### IAM Access Analyzer
#### Features
- Analyze resource policies for external access
- Identify unintended access
- Generate policy based on access patterns
- Validate policies against best practices

#### Use Cases
- Security auditing
- Policy validation
- Access review
- Compliance verification

## AWS Cognito

### Overview
- Identity management for web and mobile applications
- User sign-up, sign-in, and access control
- Integration with social identity providers
- Scalable user directory service

### Cognito User Pools
#### Features
- User directory with sign-up and sign-in
- Built-in user interface components
- MFA and security features
- Social and enterprise identity federation

#### User Pool Features
- User registration and authentication
- Password policies and account recovery
- Email and phone verification
- Custom attributes and workflows

#### Integration
- Mobile and web applications
- API Gateway authorization
- Application Load Balancer authentication
- Custom authentication flows

### Cognito Identity Pools (Federated Identities)
#### Features
- Provide AWS credentials to users
- Support authenticated and unauthenticated access
- Role-based access control
- Temporary, limited-privilege credentials

#### Identity Providers
- Cognito User Pools
- Social providers (Google, Facebook, Amazon)
- Enterprise providers (SAML, OpenID Connect)
- Developer-authenticated identities

#### Use Cases
- Mobile app AWS resource access
- Web application AWS integration
- IoT device authentication
- Fine-grained AWS permissions

### Cognito Sync (Deprecated)
#### Features
- Synchronize user data across devices
- Offline capability
- Conflict resolution
- Being replaced by AWS AppSync

### Security Features
#### Multi-Factor Authentication
- SMS-based MFA
- Time-based One-Time Password (TOTP)
- Software token MFA
- Custom MFA challenges

#### Advanced Security Features
- Compromised credential detection
- Risk-based authentication
- Device tracking and management
- Adaptive authentication

## AWS Directory Service

### Overview
- Managed directory services in AWS cloud
- Microsoft Active Directory compatibility
- Multiple deployment options
- Integration with AWS services

### AWS Managed Microsoft AD
#### Features
- Fully managed Microsoft Active Directory
- Windows Server 2012 R2 or 2019
- Trust relationships with on-premises AD
- Multi-AZ deployment for high availability

#### Use Cases
- Migrate existing AD-dependent applications
- Hybrid cloud directory integration
- SharePoint, .NET, and SQL Server workloads
- Single sign-on for AWS applications

### AD Connector
#### Features
- Directory gateway to on-premises Active Directory
- No caching of directory information
- Redirect directory requests to on-premises
- Scale to thousands of users

#### Use Cases
- Use existing on-premises AD
- Enable AWS services to use on-premises AD
- Single sign-on to AWS Management Console
- Existing AD authentication for applications

### Simple AD
#### Features
- Standalone managed directory
- Samba 4 Active Directory compatible
- Basic AD features subset
- Small and large sizing options

#### Use Cases
- Simple user directory requirements
- Linux workloads requiring LDAP
- Basic AD features sufficient
- Cost-effective directory solution

### Directory Service Features
#### Trust Relationships
- One-way and two-way trusts
- Forest and external trusts
- Cross-account directory sharing
- Hybrid identity management

#### Integration
- Amazon WorkSpaces
- Amazon WorkDocs
- Amazon QuickSight
- AWS Single Sign-On

## AWS Single Sign-On (AWS SSO)

### Overview
- Centrally manage SSO access
- AWS accounts and cloud applications
- Built-in identity store or external providers
- Permission sets for access control

### Features
#### User Portal
- Single portal for all assigned applications
- AWS account access
- Cloud application access
- Mobile application support

#### Permission Sets
- Collection of policies
- Map to IAM roles in AWS accounts
- Consistent permissions across accounts
- Centralized management

#### Identity Sources
- Built-in identity store
- AWS Managed Microsoft AD
- External identity providers (SAML 2.0)
- Active Directory connector

### Multi-Account Management
#### AWS Organizations Integration
- Manage access across organization
- Account-level permissions
- Organizational unit permissions
- Centralized user management

#### Account Assignment
- Assign users to AWS accounts
- Role-based access control
- Time-limited access
- Audit and compliance

## AWS Key Management Service (KMS)

### Overview
- Managed encryption key service
- Create and control encryption keys
- Integrate with AWS services
- Hardware Security Modules (HSMs)

### Key Types
#### Customer Managed Keys
- Full control over key lifecycle
- Enable/disable keys
- Key policies and grants
- Automatic key rotation (optional)

#### AWS Managed Keys
- Created and managed by AWS services
- Used by AWS services on your behalf
- No direct key management required
- Automatic rotation enabled

#### AWS Owned Keys
- Owned and managed by AWS
- Used by AWS services
- No customer visibility or control
- No additional charges

### Key Material Origin
#### KMS Generated
- AWS KMS generates key material
- Default option for most use cases
- Automatic key rotation available
- Highest security and availability

#### Customer Provided (External)
- Customer provides key material
- Import your own keys
- Customer responsible for key material
- No automatic rotation

#### CloudHSM Key Store
- Keys stored in CloudHSM cluster
- Higher performance for crypto operations
- Custom key store for compliance
- Single-tenant key storage

### Encryption Operations
#### Envelope Encryption
- Data encrypted with data encryption key (DEK)
- DEK encrypted with key encryption key (KEK)
- Efficient for large data volumes
- Reduces calls to KMS

#### Direct Encryption
- Encrypt small data directly with KMS
- Up to 4 KB data size limit
- Simple for small secrets
- Higher latency for repeated operations

### Key Policies
#### Resource-Based Policies
- JSON policy documents
- Control access to KMS keys
- Principal, action, resource, condition elements
- Combine with IAM policies

#### Grants
- Programmatic access delegation
- Token-based permissions
- Temporary access to keys
- Revocable permissions

### Integration with AWS Services
#### Encryption at Rest
- S3, EBS, RDS, DynamoDB
- Transparent encryption
- Service-specific configurations
- Cross-service key sharing

#### Encryption in Transit
- Application-level encryption
- SDK and CLI integration
- Certificate and key management
- End-to-end encryption

## AWS CloudHSM

### Overview
- Hardware Security Module in AWS cloud
- FIPS 140-2 Level 3 validation
- Customer-managed encryption keys
- Single-tenant key storage

### Features
#### Hardware Security
- Tamper-resistant hardware
- Dedicated HSM instances
- Customer-controlled access
- High-performance crypto operations

#### Integration
- PKCS#11, Java Cryptography Architecture (JCA)
- Microsoft CryptoNG (CNG)
- OpenSSL integration
- Custom application integration

### Use Cases
#### Compliance Requirements
- Meet strict compliance standards
- FIPS 140-2 Level 3 requirements
- Common Criteria certification
- Government and enterprise workloads

#### High-Performance Cryptography
- SSL/TLS certificate authorities
- Database encryption
- Code signing certificates
- Custom cryptographic applications

### CloudHSM vs KMS
#### CloudHSM Benefits
- Dedicated hardware
- Custom key management
- Higher performance
- Full customer control

#### KMS Benefits
- Fully managed service
- AWS service integration
- Lower cost
- Automatic scaling

## AWS Certificate Manager (ACM)

### Overview
- Provision and manage SSL/TLS certificates
- Automatic certificate renewal
- Integration with AWS services
- No additional charges for certificates

### Certificate Types
#### Public Certificates
- Domain-validated certificates
- Free with ACM
- Automatic renewal
- Integration with AWS services

#### Private Certificates
- Internal applications and services
- Private certificate authority (CA)
- Pay per certificate issued
- Custom certificate policies

### Integration Services
#### Load Balancers
- Application Load Balancer
- Network Load Balancer
- Classic Load Balancer
- Automatic certificate deployment

#### CloudFront
- Global content delivery
- SSL/TLS termination at edge
- Custom domain support
- Automatic certificate management

#### API Gateway
- Custom domain names
- Regional and edge endpoints
- Automatic certificate provisioning
- SSL/TLS encryption

### Certificate Validation
#### DNS Validation
- Add CNAME record to DNS
- Automatic validation
- Recommended method
- Works with Route 53

#### Email Validation
- Email to domain admin addresses
- Manual validation process
- Less automated than DNS
- Backup validation method

## AWS Secrets Manager

### Overview
- Securely store and retrieve secrets
- Automatic secret rotation
- Fine-grained access control
- Integration with AWS services

### Secret Types
#### Database Credentials
- RDS database passwords
- Automatic rotation support
- Connection string management
- Multiple database engines

#### API Keys and Tokens
- Third-party service credentials
- OAuth tokens
- Service account keys
- Custom secrets

#### Other Secrets
- SSH keys
- TLS certificates
- License keys
- Configuration parameters

### Automatic Rotation
#### Supported Services
- Amazon RDS
- Amazon Aurora
- Amazon DocumentDB
- Amazon Redshift

#### Custom Rotation
- Lambda function for rotation logic
- Custom rotation schedules
- Multi-user rotation strategies
- Cross-region rotation

### Access Control
#### Resource Policies
- Fine-grained access control
- Cross-account access
- Condition-based policies
- Principal-based restrictions

#### Encryption
- Encryption at rest with KMS
- Encryption in transit
- Regional key storage
- Version control

## AWS Parameter Store

### Overview
- Secure storage for configuration data
- Hierarchical parameter organization
- Integration with AWS services
- Free tier available

### Parameter Types
#### Standard Parameters
- Up to 4 KB value size
- No additional charges
- Basic data types
- Simple configuration data

#### Advanced Parameters
- Up to 8 KB value size
- Parameter policies
- Parameter history
- Additional features

### Data Types
#### String
- Plain text values
- Configuration parameters
- Simple data storage
- Most common type

#### StringList
- Comma-separated values
- Multiple related values
- List of configuration items
- Array-like data

#### SecureString
- Encrypted with KMS
- Sensitive configuration data
- Passwords and secrets
- Enhanced security

### Parameter Hierarchy
#### Organization
- Use forward slashes (/) for hierarchy
- Group related parameters
- Environment-specific parameters
- Application-specific organization

#### Examples
- `/myapp/database/hostname`
- `/myapp/database/username`
- `/prod/api/key`
- `/dev/database/password`

### Integration
#### Systems Manager
- Patch management
- Run Command operations
- State Manager configurations
- Automation workflows

#### Application Integration
- AWS SDKs
- Parameter Store APIs
- Environment variable injection
- Configuration management

## Security Best Practices

### Identity and Access Management
#### Principle of Least Privilege
- Grant minimum required permissions
- Regular access reviews
- Role-based access control
- Time-limited access when possible

#### Multi-Factor Authentication
- Enable MFA for privileged accounts
- Use hardware tokens for sensitive operations
- Require MFA for AWS Console access
- Monitor MFA usage and compliance

#### Access Key Management
- Rotate access keys regularly
- Use temporary credentials when possible
- Don't embed keys in application code
- Monitor key usage patterns

### Data Protection
#### Encryption Strategy
- Encrypt data at rest and in transit
- Use AWS managed encryption keys
- Implement client-side encryption for sensitive data
- Regular key rotation policies

#### Secret Management
- Use AWS Secrets Manager for sensitive data
- Avoid hardcoded secrets in applications
- Implement automatic secret rotation
- Monitor secret access patterns

#### Data Classification
- Classify data based on sensitivity
- Implement appropriate protection measures
- Document data handling procedures
- Regular data inventory and review

### Network Security
#### VPC Security
- Use private subnets for sensitive resources
- Implement defense in depth
- Regular security group audits
- Monitor network traffic patterns

#### Endpoint Security
- Use VPC endpoints for AWS service access
- Implement WAF for web applications
- Monitor and log network activities
- Regular security assessments

### Monitoring and Compliance
#### Logging and Monitoring
- Enable CloudTrail for API auditing
- Use CloudWatch for metric monitoring
- Implement security incident response
- Regular log analysis and review

#### Compliance Frameworks
- Understand applicable compliance requirements
- Implement appropriate controls
- Regular compliance assessments
- Document security procedures

### Incident Response
#### Preparation
- Develop incident response procedures
- Train security team members
- Test incident response plans
- Maintain emergency contact lists

#### Detection and Analysis
- Monitor for security events
- Analyze potential security incidents
- Document investigation procedures
- Coordinate with AWS support when needed

#### Containment and Recovery
- Isolate affected resources
- Implement containment measures
- Restore from secure backups
- Document lessons learned

## Key Exam Tips

### IAM Best Practices
#### Policy Design
- Use managed policies when possible
- Implement policy conditions for enhanced security
- Regular policy reviews and updates
- Test policies before production deployment

#### Role vs User Decision
- Use roles for applications and services
- Use users for individuals requiring long-term access
- Prefer temporary credentials over permanent
- Implement cross-account access via roles

### Security Service Selection
#### KMS vs CloudHSM
- KMS for most encryption needs
- CloudHSM for compliance requirements
- Consider performance requirements
- Evaluate cost implications

#### Secrets Manager vs Parameter Store
- Secrets Manager for sensitive data with rotation
- Parameter Store for configuration data
- Consider cost and feature requirements
- Evaluate integration needs

### Common Security Scenarios
#### Cross-Account Access
- Use cross-account roles
- Implement external ID for third-party access
- Regular access reviews
- Monitor cross-account activities

#### Encryption Requirements
- Understand compliance requirements
- Implement appropriate encryption methods
- Consider performance implications
- Plan for key management

#### Identity Federation
- Choose appropriate identity provider
- Implement proper attribute mapping
- Test federation scenarios
- Monitor federated access
