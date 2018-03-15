# propel

Lee Spector's Plushy fork of Tom Helmuth's little PushGP implementation in Clojure.

## Usage

To run PushGP on the default genetic programming problem from a REPL, load propel.core into your REPL (i.e. `lein repl`), and run `(-main)`.

To run PushGP on the default genetic programming problem from command line, execute `lein run`. Command-line arguments may be provided to override the defaults specified in `-main`, for example, `lein run :population-size 100`. You can use something like `lein run | tee outfile` to send output both to the terminal and to `outfile`.

## Description

Propel is a minimalist implementation of the Push programming language and the PushGP genetic programming system in Clojure.

For more information on Push and PushGP see [http://pushlanguage.org](http://pushlanguage.org).

Propel was developed largely as a teaching tool, with the goal of conveying the core concepts of Push and PushGP as clearly and concisely as possible.

As of this writing, only a few data types and instructions are provided, but the intention is for these to serve as models for additions.

All of the code is in [src/propel/core.clj](https://github.com/lspector/propel/blob/master/src/propel/core.clj), while a [Gorilla REPL](http://gorilla-repl.org) worksheet showing a few examples (to which you can add in your copy) is in [worksheet.clj](https://github.com/lspector/propel/blob/master/worksheet.clj). You can view the repository's version of the worksheet, formatted (read-only), [here](http://viewer.gorilla-repl.org/view.html?source=github&user=lspector&repo=propel&path=worksheet.clj).

### The Plushy Part

In a genetic programming system, programs are randomly varied and recombined, with the goal of finding a program that serves a specified purpose. 

In what form should the programs be represented for the sake of random variation and recombination? 

Push programs are parenthesized lists of instructions and values, which can include sub-programs in sub-lists.

In early versions of PushGP, Push programs were varied and recombined in their "natural" form, as possibly-nested lists of instructions and values that could be added, deleted, and replaced at any level of nesting.

Later, however, the linear (un-nested) representations of Push programs were developed, which:

- Provide better support for *uniform* variation operators (in which each program component has an equal likelihood of being affected).

- Increase the likelihood that programs will be nested where nesting matters, to form code blocks that are executed conditionally or repeatedly.

When these linear representations are used, they are translated into ordinary, possibly-nested Push programs prior to execution. The placement of opening parentheses is determined implicitly, by the positions of instructions that make use of nested code blocks. The placement of closing parentheses is indicated explicitly, in the linear representation.

In the Plush representation that is used in the [Clojush](https://github.com/lspector/Clojush) and [PyshGP](https://github.com/erp12/pyshgp), markers are attached to instructions to indicate how many closing parentheses should be inserted after that instruction is translated.

Here, we instead use the "Plushy" representation, in which we allow `close` instructions to be included in the linear sequences of instructions. These are converted into closing parentheses during translation to Push programs.



