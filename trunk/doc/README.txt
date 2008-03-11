Arc plugin for IntelliJ IDEA 7.0+
Version 0.1 - 2008/03/10
----------------------------------------------------------------------------------------

Introduction
------------

Thank you for trying out this pre-pre-pre-alpha version of the Arc plugin
for IntelliJ IDEA, versions 7 and higher. My goal is to create a pleasant
development environment for writing Arc code. In particular, I'm trying to
build something for people like me - "blub" programmers who want to take a
step into Lisp, without losing all of the nice IDE features to which we've
become accustomed (e.g., built-in support for working with source control).
I've been inspired tremendously by the Cusp project, which is a lovely
Common Lisp plugin for Eclipse.

Comments and issues should be posted at the website for this plugin:

http://code.google.com/p/intelli-arc/



Features
--------

Admittedly, the plugin doesn't yet do much...

- Simple syntax highlighting, with customizable fonts and colors
- Arc REPL
- Run selected code (option-R on the Mac, although this is customizable)
- Load current file  (option-L on the Mac, although this is customizable)
- Simple structure view, showing function and macro definitions in the current file



Requirements
------------

- MzScheme 352 or 360 (or any version which runs Arc)
  - Available at http://plt-scheme/mzscheme
- Arc
  - See http://www.arclanguage.org/install for details
- IntelliJ 7 or higher



Known Issues
------------
- In principle, the plugin should work on any platform that runs IntelliJ and MzScheme.
   In practice, I've only tested it on my machine - and Intel-based Mac running 10.4.
- Many of the built-in tools that look available, aren't (e.g., rename refactoring).
- Abnormal termination of IntelliJ will rapidly send MzScheme into outer space,
   consuming your entire machine, and possibly the universe. You might want to keep
   the Activity Monitor open if you have a tendency to kill your IntelliJ process...



Thanks,
Kurt Christensen