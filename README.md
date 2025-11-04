# Linked_List_Data_Structure

A Java implementation of the linked list data structure.

## Overview

This repository provides a clear, educational implementation of linked lists in Java. Linked lists are linear data structures where elements (nodes) are linked using pointers. This structure allows for efficient insertions and deletions compared to arrays.

## Features

- **Singly Linked List** implementation
- Basic operations: insertion, deletion, searching, traversal
- Clear and well-documented code
- Suitable for understanding linked list fundamentals

## Getting Started

### Prerequisites

- Java JDK 8 or newer
- Make (optional, for Doxygen generation)
- Doxygen (also optional)

## Usage

The repository includes example usage inside the `Main` class:

```java
LinkedList list = new LinkedList();
list.insert(5);
list.insert(10);
list.delete(5);
list.print();
```

## Documentation

The repository includes doxygen comments, to generate a pdf documentation file
install doxygen and create a Doxyfile with the recommended configuration:

```terminaloutput
PROJECT_NAME           = "Linked List Implementation"
PROJECT_BRIEF          = "A generic doubly linked list implementation in Java"
OUTPUT_DIRECTORY       = docs
INPUT                  = .
FILE_PATTERNS          = *.java
RECURSIVE              = YES
GENERATE_LATEX         = NO
GENERATE_HTML          = YES
JAVADOC_AUTOBRIEF      = YES
QT_AUTOBRIEF           = YES
ALIASES                = "complexity=\par Complexity:\n"
```
then run this command on the root folder of the project that has the configuration file:

```terminaloutput
doxygen Doxygen
```

the program should output a /docs/latex/ folder, run make this make command to build the pdf file:

```terminaloutput
make pdf
```
## Author

- Rene Garmatter
