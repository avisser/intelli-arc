Arc plugin for IntelliJ IDEA 7.0+
Version 0.2 - 2008/03/13
----------------------------------------------------------------------------------------

Requirements
------------

- MzScheme 352 or 360 (or any version which runs Arc)
  - Available at http://plt-scheme/mzscheme
- Arc
  - See http://www.arclanguage.org/install for details
- IntelliJ 7 or higher



Quick Start
-----------

To get started, install the plugin, and specify your MzScheme and Arc home
directories. Then open up (or create) a *.arc file, and begin playing.



Features
--------

Admittedly, the plugin doesn't yet have much IDE-goodness...

- Simple syntax highlighting, with customizable fonts and colors
- Arc REPL
- Run selected code (option-R on the Mac, although this is customizable)
- Load current file  (option-L on the Mac, although this is customizable)
- Simple structure view, showing function and macro definitions in the current file



Known Issues
------------
- The lexer is messing up block comments, betraying my sad ignorance of regexes.
- Abnormal termination from debug mode will leave MzScheme running, quickly eating all your RAM.
- The REPL doesn't currently support dragging and dropping text from the editor.
- I haven't yet provided any localizations.
- ...and much, much more!



Bug Reporting
-------------

Comments, issues, bugs or feature requests should be posted at the website
for this plugin:

http://code.google.com/p/intelli-arc/



And now a word from our sponsor...
----------------------------------

Thank you for trying out this pre-pre-pre-alpha version of the Arc plugin
for IntelliJ IDEA, versions 7 and higher. My goal is to create a pleasant
development environment for writing Arc code. In particular, I'm trying to
build something for people like me - "blub" programmers who want to take a
step into Lisp, without losing all of the nice IDE features to which we've
become accustomed (e.g., built-in support for working with source control).
I've been inspired tremendously by the Cusp project, which is a lovely
Common Lisp plugin for Eclipse.



Thanks in advance for your patience and your feedback,
Kurt Christensen