# Name_Surfer

Name Surfer is a Java program designed to analyze and visualize the popularity of baby names over multiple decades using data from the Social Security Administration (SSA). The application allows users to search for specific names and view their historical ranking.

## Features
- **Data Analysis**: The program reads baby names data from a provided .txt file, processing the popularity of names across various decades.
- **Search Functionality**: Users can input a name, and the program will return its rank over the specified decades.
- **Ranking**: Names are ranked based on their popularity, allowing users to see trends and changes in name preferences over time.

## Classes Overview

- **NameRecord**: Represents a record of a baby name, including attributes like name and its popularity over various years.
- **Names**: Manages a collection of `NameRecord` objects, facilitating operations like searching and sorting.
- **NameSurfer**: The driver class that interacts with the user, taking input and displaying results.

## Usage 
- When the program runs, it will prompt the user to enter a baby name.
- After entering a name, the program will display the rank of the name across different decades.
- Users can repeat the process to search for other names or exit the program.

## Authors
- [Khanh Van](https://www.github.com/kvan278)
