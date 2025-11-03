# AWS Management and Governance

## AWS CloudFormation

### Overview
- Infrastructure as Code (IaC) service
- Model and provision AWS resources
- Template-based resource creation
- Declarative infrastructure management

### CloudFormation Templates
#### Template Structure
- **AWSTemplateFormatVersion**: Template version
- **Description**: Template description
- **Metadata**: Additional template information
- **Parameters**: Input values for template
- **Mappings**: Static lookup tables
- **Conditions**: Control resource creation
- **Transform**: Serverless Application Model
- **Resources**: AWS resources to create (required)
- **Outputs**: Return values

#### Template Formats
- JSON format
- YAML format (recommended)
- Easier to read and write
- Comments supported in YAML

#### Example Template Structure
```yaml
AWSTemplateFormatVersion: '2010-09-09'
Description: 'Sample template'
Parameters:
  InstanceType:
    Type: String
    Default: t3.micro
Resources:
  MyEC2Instance:
    Type: AWS::EC2::Instance
    Properties:
      InstanceType: !Ref InstanceType
      ImageId: ami-12345678
Outputs:
  InstanceId:
    Value: !Ref MyEC2Instance
```

### CloudFormation Stacks
#### Stack Operations
- **Create**: Deploy template resources
- **Update**: Modify existing stack
- **Delete**: Remove all stack resources
- **Import**: Import existing resources

#### Stack Policies
- JSON documents controlling updates
- Protect critical resources
- Allow/deny update actions
- Stack-level protection

#### Stack Sets
- Deploy stacks across multiple accounts/regions
- Centralized management
- Automated deployment
- Permission management

### Resource Management
#### Resource Properties
- Required and optional properties
- Property validation
- Resource dependencies
- Update behavior

#### Resource Dependencies
- Implicit dependencies (!Ref, !GetAtt)
- Explicit dependencies (DependsOn)
- Creation order management
- Deletion order (reverse creation)

#### Resource Attributes
- **Ref**: Return resource identifier
- **GetAtt**: Return resource attribute
- **GetAZs**: List Availability Zones
- **Join**: Concatenate values

### Template Functions
#### Intrinsic Functions
- **!Ref**: Reference parameters and resources
- **!GetAtt**: Get resource attributes
- **!Join**: Join values with delimiter
- **!Split**: Split string into list
- **!Select**: Select value from list
- **!Sub**: Substitute variables
- **!Base64**: Encode to Base64
- **!Cidr**: Generate CIDR blocks

#### Conditional Functions
- **!If**: Conditional resource creation
- **!Not**: Logical NOT
- **!Equals**: Equality comparison
- **!And**: Logical AND
- **!Or**: Logical OR

### Update Behaviors
#### Update Types
- **Update with No Interruption**: No downtime
- **Update with Some Interruption**: Temporary disruption
- **Replacement**: Resource recreated

#### Update Policies
- Auto Scaling group updates
- CodeDeploy application updates
- Lambda function updates
- Rolling updates

#### Change Sets
- Preview stack changes
- Review before applying
- Understand update impacts
- Execute or discard changes

### Nested Stacks
#### Features
- Modular template design
- Reusable components
- Separate lifecycle management
- Cross-stack references

#### Benefits
- Template organization
- Code reusability
- Team collaboration
- Simplified maintenance

### Stack Drift Detection
#### Features
- Detect configuration drift
- Compare actual vs expected state
- Resource-level drift information
- Scheduled drift detection

#### Drift Types
- **IN_SYNC**: No drift detected
- **MODIFIED**: Resource properties changed
- **DELETED**: Resource deleted outside CloudFormation
- **NOT_CHECKED**: Drift detection not supported

### CloudFormation Registry
#### Features
- Discover and provision third-party resources
- Custom resource types
- Community and partner extensions
- Version management

#### Resource Types
- AWS native resources
- Third-party resources
- Custom resources
- Community resources

### Best Practices
#### Template Design
- Use parameters for flexibility
- Implement proper naming conventions
- Use cross-stack references
- Version control templates

#### Security
- Use IAM roles for CloudFormation
- Validate templates before deployment
- Use least privilege principles
- Encrypt sensitive parameters

#### Testing
- Validate templates syntax
- Test in non-production environments
- Use change sets for production
- Implement CI/CD pipelines

## AWS Config

### Overview
- Configuration management and compliance service
- Track resource configurations
- Evaluate compliance against rules
- Configuration history and relationships

### Config Components
#### Configuration Items (CIs)
- Point-in-time configuration snapshots
- Resource properties and metadata
- Relationships to other resources
- Configuration state changes

#### Configuration Recorder
- Records resource configurations
- Tracks configuration changes
- Stores in S3 bucket
- Delivery channel for notifications

#### Config Rules
- Evaluate resource compliance
- AWS managed rules
- Custom rules (Lambda functions)
- Remediation actions

### Resource Coverage
#### Supported Resources
- EC2 instances, security groups, VPCs
- S3 buckets and bucket policies
- IAM users, roles, and policies
- RDS instances and security groups
- CloudTrail trails
- 100+ AWS resource types

#### Global vs Regional
- Regional resources in each region
- Global resources (IAM) recorded once
- Cross-region relationships
- Centralized compliance view

### Config Rules
#### AWS Managed Rules
- Pre-built compliance checks
- Common security and governance requirements
- Automatically updated by AWS
- No additional development required

#### Custom Rules
- Lambda function-based evaluations
- Custom business logic
- Triggered by configuration changes
- Scheduled evaluations

#### Rule Evaluation
- **Change-triggered**: Evaluate on configuration changes
- **Periodic**: Evaluate on schedule
- **Configuration change**: When specific resources change
- **Tag-based**: Evaluate resources with specific tags

### Compliance Monitoring
#### Compliance Dashboard
- Overall compliance status
- Rule-specific compliance
- Resource compliance timeline
- Non-compliant resources

#### Compliance by Rules
- Rule evaluation results
- Compliant vs non-compliant resources
- Historical compliance trends
- Remediation tracking

### Configuration History
#### Features
- Track configuration changes over time
- Resource relationship changes
- Timeline view of changes
- Point-in-time configuration queries

#### Configuration Timeline
- Chronological view of changes
- Change details and differences
- Resource state at specific times
- Impact analysis

### Remediation
#### AWS Config Remediation
- Automatic remediation actions
- AWS Systems Manager automation
- Manual remediation workflows
- Remediation tracking

#### Remediation Actions
- SSM automation documents
- Lambda function execution
- API calls to AWS services
- Custom remediation logic

### Multi-Account Management
#### AWS Config Aggregator
- Centralized compliance view
- Cross-account configuration data
- Multi-region aggregation
- Organization-wide compliance

#### Account Setup
- AWS Organizations integration
- Centralized rule deployment
- Consistent configuration
- Simplified management

### Cost Management
#### Pricing Model
- Configuration items recorded
- Rule evaluations
- Data storage costs
- API call charges

#### Cost Optimization
- Select specific resource types
- Use aggregators for multi-account
- Archive old configuration data
- Monitor rule evaluation frequency

## AWS Organizations

### Overview
- Centrally manage multiple AWS accounts
- Consolidated billing
- Account governance
- Service control policies

### Organization Structure
#### Organizational Units (OUs)
- Hierarchical grouping of accounts
- Inherit policies from parent OUs
- Logical separation of workloads
- Department or project-based organization

#### Accounts
- Member accounts in organization
- Management account (formerly master)
- Isolated billing and resources
- Service control policy inheritance

#### Root
- Top-level container
- Contains all accounts and OUs
- Policy inheritance starting point
- Organization-wide settings

### Service Control Policies (SCPs)
#### Features
- IAM-like policy syntax
- Preventive guardrails
- Applied to OUs or accounts
- Filter permissions, don't grant

#### Policy Effects
- **Allow**: Explicit permission (allowlist)
- **Deny**: Explicit denial (denylist)
- No effect on management account
- Combined with IAM policies

#### Common Use Cases
- Prevent account closure
- Restrict regions
- Enforce MFA requirements
- Limit service usage

### Consolidated Billing
#### Features
- Single payment method
- Combined usage for volume discounts
- Reserved Instance sharing
- Detailed cost and usage reports

#### Benefits
- Volume discounts across accounts
- Centralized payment management
- Cost allocation tracking
- Simplified procurement

### Account Management
#### Account Creation
- Programmatic account creation
- Automated account setup
- API and console support
- Integration with provisioning tools

#### Account Invitation
- Invite existing accounts
- Email-based invitation process
- Account acceptance required
- Transfer billing responsibility

### AWS Control Tower
#### Overview
- Service for setting up multi-account environments
- Best practice blueprints
- Automated account creation
- Continuous governance

#### Landing Zone
- Multi-account environment setup
- Security and compliance baselines
- Account factory for new accounts
- Centralized logging and monitoring

#### Guardrails
- High-level rules for governance
- Preventive and detective guardrails
- Based on SCPs and Config rules
- Compliance monitoring

### AWS Resource Access Manager (RAM)
#### Features
- Share AWS resources across accounts
- VPC subnets, Route 53 resolvers
- License Manager configurations
- Aurora clusters

#### Benefits
- Reduce resource duplication
- Centralized resource management
- Cost optimization
- Simplified administration

### Best Practices
#### Account Strategy
- Separate accounts for different environments
- Dedicated accounts for security functions
- Logging account for centralized logs
- Shared services account

#### Organizational Design
- Align OUs with business structure
- Use descriptive names
- Plan for growth and changes
- Document organizational structure

#### Security
- Enable CloudTrail in all accounts
- Centralized security monitoring
- Regular access reviews
- Principle of least privilege

## AWS Systems Manager

### Overview
- Unified interface for AWS operational data
- Automate operational tasks
- Configuration and patch management
- Secure instance access

### Systems Manager Capabilities
#### Operational Management
- OpsCenter for issue management
- Explorer for operational insights
- CloudWatch Dashboard integration
- Incident management

#### Application Management
- Application Manager
- AppConfig for configuration management
- Parameter Store for secrets
- Resource groups

#### Change Management
- Change Calendar
- Maintenance Windows
- Automation workflows
- Change tracking

#### Node Management
- Fleet Manager
- Session Manager
- Run Command
- Patch Manager

### Parameter Store
#### Features
- Secure configuration data storage
- Hierarchical parameter organization
- String, StringList, SecureString types
- Version history and labeling

#### Parameter Tiers
- **Standard**: Up to 4 KB, no charge
- **Advanced**: Up to 8 KB, parameter policies
- **Intelligent Tiering**: Automatic optimization
- Cost optimization features

#### Integration
- CloudFormation parameter references
- Lambda environment variables
- EC2 user data
- Application configuration

### Session Manager
#### Features
- Browser-based shell access
- No SSH keys or bastion hosts
- Session logging and auditing
- Cross-platform support

#### Benefits
- Enhanced security
- Simplified access management
- Audit trail
- No infrastructure overhead

### Run Command
#### Features
- Execute commands on multiple instances
- Pre-defined and custom documents
- Parallel execution
- Output logging

#### Use Cases
- Software installation
- Configuration changes
- Data collection
- Troubleshooting

### Patch Manager
#### Features
- Automated OS and application patching
- Patch baselines and groups
- Maintenance windows
- Compliance reporting

#### Patch Baselines
- Rules for patch approval
- Operating system specific
- Custom patch baselines
- Exception handling

### Automation
#### Features
- Workflow automation using documents
- Multi-step processes
- Integration with other AWS services
- Error handling and rollback

#### Automation Documents
- Pre-built automation workflows
- Custom automation logic
- Version control
- Sharing across accounts

### State Manager
#### Features
- Define and maintain instance configurations
- Ensure compliance
- Drift detection
- Automatic remediation

#### Associations
- Link documents to instances
- Schedule execution
- Target specification
- Compliance tracking

### Inventory
#### Features
- Collect instance metadata
- Software and configuration inventory
- Custom inventory types
- Aggregation and querying

#### Data Collection
- Operating system information
- Installed applications
- AWS components
- Network configuration

### Compliance
#### Features
- Configuration compliance scanning
- Patch compliance reporting
- Custom compliance rules
- Remediation workflows

#### Reporting
- Compliance dashboards
- Historical compliance data
- Resource-level details
- Trend analysis

## AWS Trusted Advisor

### Overview
- Real-time guidance for AWS best practices
- Cost optimization recommendations
- Security improvements
- Performance optimization

### Check Categories
#### Cost Optimization
- Underutilized resources
- Reserved Instance recommendations
- Idle resources
- Right-sizing opportunities

#### Performance
- Service limits approaching
- Over-utilized resources
- Performance improvements
- Configuration optimizations

#### Security
- Security group configurations
- IAM usage
- Root account usage
- Encryption settings

#### Fault Tolerance
- Auto Scaling configurations
- Multi-AZ deployments
- Backup configurations
- Single points of failure

#### Service Limits
- Service usage approaching limits
- Limit increase recommendations
- Resource planning
- Capacity management

### Support Plan Access
#### Basic Support
- 7 core checks
- Security and service limits
- No programmatic access
- Web console only

#### Developer Support
- 7 core checks
- Email support
- Business hours access
- No programmatic access

#### Business Support
- Full check suite
- Programmatic access via API
- CloudWatch integration
- Priority support

#### Enterprise Support
- Full check suite
- Technical Account Manager
- Infrastructure Event Management
- Proactive guidance

### Integration and Automation
#### CloudWatch Integration
- Trusted Advisor metrics
- Custom alarms
- Automated responses
- Trend monitoring

#### API Access
- Programmatic check results
- Automation integration
- Custom reporting
- Third-party tool integration

## AWS Well-Architected Tool

### Overview
- Review architecture against best practices
- Identify areas for improvement
- Document architectural decisions
- Track improvement over time

### Well-Architected Framework
#### Six Pillars
1. **Operational Excellence**
2. **Security**
3. **Reliability**
4. **Performance Efficiency**
5. **Cost Optimization**
6. **Sustainability**

#### Review Process
- Answer pillar-specific questions
- Identify high and medium risk issues
- Receive improvement recommendations
- Create improvement plans

### Workload Reviews
#### Features
- Structured questionnaire
- Risk assessment
- Improvement recommendations
- Progress tracking

#### Review Types
- Self-service reviews
- Architect-led reviews
- Partner-conducted reviews
- AWS-facilitated reviews

## Key Exam Tips

### Infrastructure as Code
#### CloudFormation Benefits
- Version control for infrastructure
- Consistent deployments
- Rollback capabilities
- Documentation as code

#### Template Best Practices
- Use parameters for flexibility
- Implement proper error handling
- Version control templates
- Test in non-production first

### Configuration Management
#### AWS Config Use Cases
- Compliance monitoring
- Security posture assessment
- Change tracking
- Resource relationship mapping

#### Rule Selection
- Use AWS managed rules when available
- Create custom rules for specific requirements
- Regular rule evaluation
- Automated remediation where possible

### Multi-Account Strategy
#### Organizations Benefits
- Centralized management
- Consolidated billing
- Service control policies
- Account isolation

#### Account Design
- Separate accounts by environment
- Dedicated security accounts
- Centralized logging accounts
- Shared services accounts

### Operational Excellence
#### Systems Manager Capabilities
- Centralized operational data
- Automated workflows
- Secure access management
- Configuration compliance

#### Automation Strategies
- Use automation documents
- Implement maintenance windows
- Schedule operational tasks
- Monitor automation execution

### Cost Management
#### Trusted Advisor Recommendations
- Right-size underutilized resources
- Purchase Reserved Instances
- Delete idle resources
- Optimize storage costs

#### Cost Optimization Process
- Regular cost reviews
- Implement cost controls
- Monitor usage patterns
- Automate cost optimization

### Governance and Compliance
#### Policy Management
- Use SCPs for preventive controls
- Implement Config rules for detective controls
- Regular compliance assessments
- Automated remediation workflows

#### Security Governance
- Centralized identity management
- Regular security reviews
- Automated security monitoring
- Incident response procedures
