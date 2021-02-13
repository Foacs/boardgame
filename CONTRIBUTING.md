# Contributing to RIBZ

## Table of content
- [GIT](#1git)
  - [Git rules](#11-some-git-rules)
  - [Gitflow workflow](#12-gitflow-workflow)
  - [Writing good commit message](#13-writing-good-commit-messages)
- [DOCUMENTATION](#2documentation)
- [DEPENDENCIES](#3dependencies)
- [TESTING](#4testing)
- [STRUCTURE AND NAMING](#5structure-and-naming)
- [CODE STYLE](#6code-style)

## 1.Git

The purposes of a unified git workflow is:

* Define branches role
* Ensure branches have the appropriate code
* Ensure branches states - mainly keep `master` deployable at any time

### 1.1 Some Git Rules

There are a set of rules to keep in mind:

- Perform work in a feature or fix branch.

  _Why:_

> All work is done in isolation on a dedicated branch rather than the main branch. It allows
> you to submit multiple pull requests without confusion. You can iterate without polluting the master branch
> with potentially unstable, unfinished code.

- Never push into `master` or `develop` branches. Make a Pull Request.
  _Why:_

> It notifies team members that they have completed a feature. It also enables easy peer-review of the code
> and dedicates a forum for discussing the proposed feature.

- Delete local and remote feature/fix branches after merging.
  _Why:_

> It will clutter up your list of branches with dead branches. It ensures you only ever merge the branch back
> into (`develop`) once. Feature branches should only exist while the work is still in progress.

- Before making a Pull Request, make sure your feature branch builds successfully and passes all
  tests (including code style checks).
  _Why:_

> You are about to add your code to a stable branch. If your feature-branch tests fail, there is a high chance
> that your destination branch build will fail too. Additionally, you need to apply code style check before
> making a Pull Request. It aids readability and reduces the chances of formatting fixes being mingled in with
> actual changes.

### 1.2 Gitflow workflow

For a complete comprehension of gitflow workflow, please refer to
this [Atlasian's guide](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
. Briefly:

- The `master` branch contains **released** code and is tagged with release version.
- The `develop` branch, created from `master`, contains **developed** code for next release.
- The `feature` or `fix` branches contain code **under development**. When development is done, the
  code is put on develop via a pull request.

#### How to proceed

- Checkout a new feature/fix branch.
    ```shell script
    git checkout -b <branchname>
    ```
  Branch name should follow this template `feature/<feature-name>` or `fix/<issue-number>`.
- Make changes.
    ```shell script
    git add <file1> <file2> ...
    git commit
    ```
  _Why:_
  > `git add <file1> <file2> ...` - you should add only files that make up a small and coherent change.

  > `git commit` will start an editor which lets you separate the subject from the body.

  > Read more about it in section 1.3

- Push your branch.
    ```shell script
    git push
    ```
- Make a Pull Request.
- Pull request will be accepted, rebase and close by a reviewer.
- Remove your local feature branch if you're done.
    ````shell script
    git branch -d <branchname>
    ````

### 1.3 Writing good commit messages

- Separate the subject from the body with a newline between the two.

  _Why:_
  > Git is smart enough to distinguish the first line of your commit message as your summary. In fact, if you try
  > git shortlog, instead of git log, you will see a long list of commit messages, consisting of th id of the commit,
  > and the summary only.
- Limit the subject line to 50 characters and Wrap the body at 72 characters.

  _Why:_
  > Commits should be as fine-grained and focused as possible, it is not the place to be verbose.
- Capitalize the subject line.
- Do not end the subject line with a period.
- Use imperative mood in the subject line.

  _Why:_
  > Rather than writting messages that say what a commiter has done. It's better to consider these messages
  > as the instruction for what is going to be done after the commit is applied on the repository.
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
The styles used for this project are:

- Java: [Google Java Style](https://google.github.io/styleguide/javaguide.html)
- Markdown: [Prettier Markdown](https://prettier.io/blog/2017/11/07/1.8.0.html)
- YAML: [Prettier Yaml](https://prettier.io/blog/2018/07/29/1.14.0.html)