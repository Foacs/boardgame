# Contributing to RIBZ

## Table of content
- [GIT](#1git)
    - [GIT rules](#11-some-git-rules)
    - [GIT workflow](#12-git-workflow)
    - [Writing good commit message](#13-writting-good-commit-messages)
- [DOCUMENTATION](#2documentation)
- [DEPENDENCIES](#3dependencies)
- [TESTING](#4testing)
- [STRUCTURE AND NAMING](#5structure-and-naming)
- [CODE STYLE](#6code-style)
    -[Google Java Style](#61-google-java-style)
    
## 1.Git
The branching strategy apply to this project follows the [GitHub flow](https://guides.github.com/introduction/flow/).
### 1.1 Some Git Rules
There are a set of rules to keep in mind:
- Perform work in a feature branch.

    _Why:_
> Because this way all work is done in isolation on a dedicated branch rather than the main branch. It allows
> you to submit multiple pull requests without confudion. You can iterate without polluting the master branch
>with potentially unstable, unfinished code. 

- Never push into `master` branch. Make a Pull Request.
    _Why:_
>It notifies team members that they have completed a feature. It also enables easy peer-review of the code 
>and dedicates forum for dicussing the proposed feature.

- Delete local and remote feature branches after merging.
    _Why:_
>It will clutter up your list of branches with dead branches. It ensures you only ever merge the branch back
>into (`master`) once. Feature branches should only exist while the work is still in progress.

- Before making a Pull Request, make sure your feature branch builds successfully and passes all tests (including code style checks).
    _Why:_
>You are about to add your code to a stable branch. If your feature-branch tests fail, there is a high chance 
>that your destination branch build will fail too. Additionally, you need to apply code style check before
>making a Pull Request. It aids readabillity and reduces the chances of formatting fixes being mingled in with
>actual changes.


### 1.2 Git workflow
- Checkout a new feature/bug-fix branch.
    ```shell script
    git checkout -b <branchname>
    ```
    Branch name should follow this template `feature/<feature-name>` or `bug-fix/<issue-number>`.
- Make changes.
    ```shell script
    git add <file1> <file2> ...
    git commit
    ```
    _Why:_
    >`git add <file1> <file2> ...` - you shoudl add only files that make up a small and coherent change.

    >`git commit` will start an editor which lets you separate the subject from the body.

    > Read more about it in section 1.3

- Push your branch.
    ```shell script
    git push
    ```
- Make a Pull Request.
- Pull request will be accepted, merged and close by a reviewer.
- Remove your local feature branch if you're done.
    ````shell script
    git branch -d <branchname>
    ````
### 1.3 Writting good commit messages
- Separate the subject from the body with a newline between the two.

    _Why:_
    >Git is smart enough to distinguish the first line of your commit message as your summary. In fact, if you try
    >git shortlog, instead of git log, you will see a long list of commit messages, consisting of th id of the commit, 
    >and the summary only. 
- Limit the subject line to 50 characters and Wrap the body at 72 characters.

    _Why:_
    >Commits should be as fine-grained and focused as possible, it is not the place to be verbose.
- Capitalize the subject line.
- Do not end the subject line with a periode.
- Use imperative mood in the subject line.

    _Why:_
    >Rather than writting messages that say what a commiter has done. It's better to consider these messages
    >as the instruction for what is going to be done after the commit is applied on the repository.
- Use the body to explain __what__ and __why__ as opposed to __how__.

## 2.Documentation
- Comment your code. Try to make it as clear as possible what you are intending with each major section.
- If there is an open discussion on github or stackoverflow about the code or approach you're using, include the
link in your comment.
- Don't use comments as an excuse for a bad code. Keep your code clean.
- Don't use clean code as an excuse to not comment at all.
- Keep comments relevant as your code evolves.

## 3.Dependencies
- Keep track of your currently available packages
- See if any of your packages have become unused or irrelevant.

    _Why:_
    >You may include an unused library in your code and increase the production bundle size. Find unused dependencies and get rid of them.
- Before using a dependency, check its download statistics to see if it is heavily used by the community.

    _Why:_
    >More usage mostly means more contributors, which usually means better maintenance, and all of these
    >result in quickly discovered bugs and quickly fixes.
- Before using a dependency, check to see if it has a good, mature version release frequency with a large 
number of maintainers.

    _Why:_
    >Having loads of contributors won't be as effective if maintainers don't merge fixes and patches quickly enough.
- If a less known dependency is needed, discuss it with the team before using it.
- Always make sure your app works with the latest version of its dependencies without breaking.
- Check to see if the package has known security vulnerabilities with, e.g., [Snyk](https://snyk.io/test/)

## 4.Testing
- Write testable code, avoid side effects, extract side effects, write pure functions.

    _Why:_
    >You want to test a business logic as separate units. You have to "minimize the impact of randomness and 
    >nondeterministic processes on the reliability of your code".
    
    >A pure function is a function that always returns the same output for the same input. Conversely, an
    >impure function is one that may have side effects or depends on conditions from the outside to produce a
    >value. That makes it less predictable.
- Run test locally before making any pull requests to develop.
- Document your tests including instructions in the relevant section of the `README.md` file.

## 5.Structure and Naming

## 6.Code style
To ensure that defined code style is properly apply, any pull request is checked by [restyle.io](https://restyled.io) bot.
The styles used for this project are:

- Java: [Google Java Style](https://google.github.io/styleguide/javaguide.html)
- Markdown: [Prettier Markdown](https://prettier.io/blog/2017/11/07/1.8.0.html)
- YAML: [Prettier Yaml](https://prettier.io/blog/2018/07/29/1.14.0.html)

### 6.1 Google Java Style
#### Source file basics
- **File Name**: consists of the case-sensitive name of the top-level class it contains, plus the `.java` extension.
- **File encoding: UTF-8**
- **Whitespace characters**: The ASCII horizontal space character (0x20) is the only character that appears anywhere in a source file.
    - All other whitespace characters in string and character literals are escaped.
    - Tab characters are __not__ used for indentation.
- **Escape**: Prefer `\b, \t, \n, \f, \r, \", \' and \\` rather than corresponding octal (e.g. `\012`) or Unicode (e.g. `\u000a`)
- **Non-ASCII characters**: Unicode escapes outside string literals and comments are strongly discouraged

### Source file structure
A source file consists of, in order:

1. License or copyright information, if present
2. Package statement
3. Import statements
4. Exactly one top-level class

__Exactly one blank line__ separates each section that is present.

- **Package statement**: The package statement is not line-wrapped. The column limit (Section 4.4, Column limit: 100) does not apply to package statements.
- **Import statements**: 
    - **Not Wildcard Import**
    - **No line-wrapping**
    - **Ordering and spacing**: Imports are ordered as follows:
    
        1. All static imports in a single block.
        2. All non-static imports in a single block.
    - **No static import for classes**
- **Class declaration**:
    - **Exactly one top-level class declaration**

### Formatting

- **Braces are used where optional**
- **Nonempty blocks: K & R style**
    - No line break before the opening brace.
    - Line break after the opening brace.
    - Line break before the closing brace.
    - Line break after the closing brace, only if that brace terminates a statement 
    or terminates the body of a method, constructor, or named class. For example, 
    there is no line break after the brace if it is followed by else or a comma.
- **Empty blocks: may be concise**: 
```
 // This is acceptable
  void doNothing() {}

  // This is equally acceptable
  void doNothingElse() {
  }

  // This is not acceptable: No concise empty blocks in a multi-block statement
  try {
    doSomething();
  } catch (Exception e) {}
```
- **Block indentation: +2 spaces**
- **One statement per line**
- **Column limit: 100**
   
    Exceptions:
    - Lines where obeying the column limit is not possible (for example, a long URL in Javadoc, or a long JSNI method reference).
    - package and import statements (see Sections 3.2 Package statement and 3.3 Import statements).
    - Command lines in a comment that may be cut-and-pasted into a shell.
    
- **Indent continuation lines at least +4 spaces**
- **Vertical Whitespace**

    A single blank line always appears:
    - Between consecutive members or initializers of a class: fields, constructors, methods, nested classes, static initializers, and instance initializers.
        - Exception: A blank line between two consecutive fields (having no other code between them) is optional. Such blank lines are used as needed to create logical groupings of fields.
        - Exception: Blank lines between enum constants are covered in Section 4.8.1.
    - As required by other sections of this document (such as Section 3, Source file structure, and Section 3.3, Import statements).

    A single blank line may also appear anywhere it improves readability, for example between statements to organize the code into logical subsections. A blank line before the first member or initializer, or after the last member or initializer of the class, is neither encouraged nor discouraged.

    Multiple consecutive blank lines are permitted, but never required (or encouraged).