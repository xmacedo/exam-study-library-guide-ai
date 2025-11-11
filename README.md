# Exam Guide AI Assistant

The idea is to create a placeholder and contain a library of study guides for exams.

## Features


## Prerequisites

1. **Java 25** or later
2. **Maven 3.9.9+**
3. **Ollama** installed and running locally

### Installing Ollama

1. Install Ollama from [https://ollama.ai](https://ollama.ai)
2. Pull the required models:
    ```bash
    ollama pull qwen2.5:32b
    ollama pull nomic-embed-text
    ```

### Installing Java 

```bash
 brew install --cask oracle-jdk@25
```

### Installing Maven
    
```bash
 brew install maven
```

## Setup and Running

1. **Navigate to the project directory:**
   ```bash
   cd /exam-study-library-guide-ai
   ```

2. **Start Ollama** (if not already running):
   ```bash
   ollama serve
   ```

3. **Build and run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Open your browser** and go to:
   ```
   http://localhost:8080
   ```