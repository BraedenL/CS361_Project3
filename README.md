# Project 3: Turing Machine

* Author: Braeden LaCombe, Andrew Lackey
* Class: CS361 Section 1
* Semester: Spring 2023

## Overview

This program creates a Turing machine and matching tape from a given input .txt file. It manages states, transitions, and other needed functions related to the bi-infinite tape connected to the machine.
## Reflection

I think for us in this project it took us a while to get things really going. The project description felt very vauge at times and unclear in how things worked. It would be a little easier if the transitions were listed in an easier to read way for sure. With not much guidance we had to keep a lot of things vauge then lock them later in debugging.

- What worked well and what was a struggle?
    - Reading the input was easy enough but getting the tape and machine connected properly was a struggle for sure.
    - Making it as fast as possible was pretty challenging as well
- What concepts still aren't quite clear?
    What do we do in certain circumstances, and what is the right classes to use for some parts of the machine functionality; Array, list, stack, etc...
    Also, why did the encoding for the transitions have to be so complicated? Would have been much easier if each line more clearly outlined, the read, write, fromState, toState, moveDirection etc. Although I suppose that was part of the challenge...
- What techniques did you use to make your code easy to debug and modify?
    - Just a lot of system prints. Where we were, using VSCode debugging tools like watching specific variables and such.
- What would you change about your design process?
    - Just some of how to set things up and track them, but was hard to start without some help
- If you could go back in time, what would you tell yourself about doing this project?
    - Start early, make things easy to change early on, refactor often, use scratch paper more often!
## Compiling and Using

Navigate to the project directory and then into the tm folder, then compile using the commands
$ javac TranInfo.java
$ javac TMState.java
$ javac TMSimulator.java

and then run the main file using

$ java TMSimulator [input_file_name.txt]

## Sources used

Some API documents for java classes and iterators and some in class questions
----------
This README template is using Markdown. To preview your README output,
you can copy your file contents to a Markdown editor/previewer such
as [https://stackedit.io/editor](https://stackedit.io/editor).
