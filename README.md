# bar

A [re-frame](https://github.com/day8/re-frame) application designed to ... well, that part is up to
you.

## Connect Calva from VS Code:

[Calva](https://github.com/BetterThanTomorrow/calva) has built-in support for `shadow-cljs` projects (that's why there is no `+calva` option for creating this project). 

0. `npm install`
1. From VS Code, issue the command **Calva: Start a Project REPL and Connect (a.k.a Jack-in)**, `ctrl+alt+c ctrl+alt+j`.
   * Calva will auto-detect that this is a `shadow-cljs` project and ask for which build to compile.
   * Calva's output window will open and log some progress information.
1. When prompted for which build to start, select `:app`.
   * `:app` is the only configured build, but the VS Code menu for this is a bit strange so _make sure the `:app` checkbox is really ticked before proceeding_.
   * This will start the app, so in this workflow you don't do the **Run application** steps outlined below.
1. When prompted for which build to connect to, select `:app`.
    * In the **View** menu of VS Code, you can tell it to show the **Terminal** view, where you see which command the jack-in process is started with, and it's output. `Ctrl+C` in this pane will kill your app and free up all resources it has allocated.
1. When the app is compiled
    1. Open http://localhost:8280 in your browser.
    1. Confirm that it says *Hello from re-frame*. (Depending on how long the app takes to compile, you might need to reload the page a few times.)
1. Open the `views.cljs` file from `src/<your-project-name>` and issue **Calva: Load Current File and Dependencies**. `ctrl+alt+c enter`.
    1. Confirm that you are connected by adding evaluating `(js/alert "Hello from Calva")` (`alt+enter` and `ctrl+enter` are your friends).
    1. Confirm that Shadow is hot reloading by changing the greeting message.


See https://calva.io for more on how to use Calva.

## Getting Started

### Project Overview

* Architecture:
[Single Page Application (SPA)](https://en.wikipedia.org/wiki/Single-page_application)
* Languages
  - Front end is [ClojureScript](https://clojurescript.org/) with ([re-frame](https://github.com/day8/re-frame))
  - CSS compilation is [Garden](https://github.com/noprompt/garden) with [Spade](https://github.com/dhleong/spade)
* Dependencies
  - UI framework: [re-frame](https://github.com/day8/re-frame)
  ([docs](https://github.com/day8/re-frame/blob/master/docs/README.md),
  [FAQs](https://github.com/day8/re-frame/blob/master/docs/FAQs/README.md)) ->
  [Reagent](https://github.com/reagent-project/reagent) ->
  [React](https://github.com/facebook/react)
  - CSS rendering: [Garden](https://github.com/noprompt/garden)
  - Keyboard event handler: [re-pressed](https://github.com/gadfly361/re-pressed)
  - Screen breakpoints tool: [BREAKING-POINT](https://github.com/gadfly361/breaking-point)
* Build tools
  - CLJS compilation, dependency management, REPL, & hot reload: [`shadow-cljs`](https://github.com/thheller/shadow-cljs)
  - Test framework: [cljs.test](https://clojurescript.org/tools/testing)
  - Test runner: [Karma](https://github.com/karma-runner/karma)
* Development tools
  - Debugging: [CLJS DevTools](https://github.com/binaryage/cljs-devtools),
  [`re-frame-10x`](https://github.com/day8/re-frame-10x),
  [re-frisk](https://github.com/flexsurfer/re-frisk)
  - Linter: [clj-kondo](https://github.com/borkdude/clj-kondo)

#### Directory structure

* [`/`](/../../): project config files
* [`.clj-kondo/`](.clj-kondo/): lint config and cache files (cache files are not tracked; see
[`.gitignore`](.gitignore))
* [`dev/`](dev/): source files compiled only with the [dev](#running-the-app) profile
  - [`user.cljs`](dev/cljs/user.cljs): symbols for use during development in the
[ClojureScript REPL](#connecting-to-the-browser-repl-from-a-terminal)
* [`resources/public/`](resources/public/): SPA root directory;
[dev](#running-the-app) / [prod](#production) profile depends on the most recent build
  - [`index.html`](resources/public/index.html): SPA home page
    - Dynamic SPA content rendered in the following `div`:
        ```html
        <div id="app"></div>
        ```
    - Customizable; add headers, footers, links to other scripts and styles, etc.
  - Generated directories and files
    - Created on build with either the [dev](#running-the-app) or [prod](#production) profile
    - `js/compiled/`: compiled CLJS (`shadow-cljs`)
      - Not tracked in source control; see [`.gitignore`](.gitignore)
* [`src/bar/styles.cljs`](src/bar/styles.cljs): CSS compilation source file (ClojureScript,
[Garden](https://github.com/noprompt/garden))
* [`src/bar/`](src/bar/): SPA source files (ClojureScript,
[re-frame](https://github.com/Day8/re-frame))
  - [`core.cljs`](src/bar/core.cljs): contains the SPA entry point, `init`
* [`test/bar/`](test/bar/): test files (ClojureScript,
[cljs.test](https://clojurescript.org/tools/testing))
  - Only namespaces ending in `-test` (files `*_test.cljs`) are compiled and sent to the test runner
* [`.github/workflows/`](.github/workflows/): contains the
[github actions](https://github.com/features/actions) pipelines.
  - [`test.yaml`](.github/workflows/test.yaml): Pipeline for testing.


### Editor/IDE

Use your preferred editor or IDE that supports Clojure/ClojureScript development. See
[Clojure tools](https://clojure.org/community/resources#_clojure_tools) for some popular options.

### Environment Setup

1. Install [JDK 8 or later](https://openjdk.java.net/install/) (Java Development Kit)
2. Install [Node.js](https://nodejs.org/) (JavaScript runtime environment) which should include
   [NPM](https://docs.npmjs.com/cli/npm) or if your Node.js installation does not include NPM also install it.
3. Install [Chrome](https://www.google.com/chrome/) or
[Chromium](https://www.chromium.org/getting-involved/download-chromium) version 59 or later
(headless test environment)
    * For Chromium, set the `CHROME_BIN` environment variable in your shell to the command that
    launches Chromium. For example, in Ubuntu, add the following line to your `.bashrc`:
        ```bash
        export CHROME_BIN=chromium-browser
       ```
4. Install [clj-kondo](https://github.com/borkdude/clj-kondo/blob/master/doc/install.md) (linter)
5. Clone this repo and open a terminal in the `bar` project root directory
6. (Optional) Setup [lint cache](https://github.com/borkdude/clj-kondo#project-setup):
    ```sh
    clj-kondo --lint "$(npx shadow-cljs classpath)"
    ```
7. Setup
[linting in your editor](https://github.com/borkdude/clj-kondo/blob/master/doc/editor-integration.md)

### Browser Setup

Browser caching should be disabled when developer tools are open to prevent interference with
[`shadow-cljs`](https://github.com/thheller/shadow-cljs) hot reloading.

Custom formatters must be enabled in the browser before
[CLJS DevTools](https://github.com/binaryage/cljs-devtools) can display ClojureScript data in the
console in a more readable way.

#### Chrome/Chromium

1. Open [DevTools](https://developers.google.com/web/tools/chrome-devtools/) (Linux/Windows: `F12`
or `Ctrl-Shift-I`; macOS: `⌘-Option-I`)
2. Open DevTools Settings (Linux/Windows: `?` or `F1`; macOS: `?` or `Fn+F1`)
3. Select `Preferences` in the navigation menu on the left, if it is not already selected
4. Under the `Network` heading, enable the `Disable cache (while DevTools is open)` option
5. Under the `Console` heading, enable the `Enable custom formatters` option

#### Firefox

1. Open [Developer Tools](https://developer.mozilla.org/en-US/docs/Tools) (Linux/Windows: `F12` or
`Ctrl-Shift-I`; macOS: `⌘-Option-I`)
2. Open [Developer Tools Settings](https://developer.mozilla.org/en-US/docs/Tools/Settings)
(Linux/macOS/Windows: `F1`)
3. Under the `Advanced settings` heading, enable the `Disable HTTP Cache (when toolbox is open)`
option

Unfortunately, Firefox does not yet support custom formatters in their devtools. For updates, follow
the enhancement request in their bug tracker:
[1262914 - Add support for Custom Formatters in devtools](https://bugzilla.mozilla.org/show_bug.cgi?id=1262914).

## Development

### Running the App

Start a temporary local web server, build the app with the `dev` profile, and serve the app,
browser test runner and karma test runner with hot reload:

```sh
npm install
npx shadow-cljs watch app
```

Please be patient; it may take over 20 seconds to see any output, and over 40 seconds to complete.

When `[:app] Build completed` appears in the output, browse to
[http://localhost:8280/](http://localhost:8280/).

[`shadow-cljs`](https://github.com/thheller/shadow-cljs) will automatically push ClojureScript code
changes to your browser on save. To prevent a few common issues, see
[Hot Reload in ClojureScript: Things to avoid](https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html#things-to-avoid).

Opening the app in your browser starts a
[ClojureScript browser REPL](https://clojurescript.org/reference/repl#using-the-browser-as-an-evaluation-environment),
to which you may now connect.

#### Connecting to the browser REPL from your editor

See
[Shadow CLJS User's Guide: Editor Integration](https://shadow-cljs.github.io/docs/UsersGuide.html#_editor_integration).
Note that `npm run watch` runs `npx shadow-cljs watch` for you, and that this project's running build ids is
`app`, `browser-test`, `karma-test`, or the keywords `:app`, `:browser-test`, `:karma-test` in a Clojure context.

Alternatively, search the web for info on connecting to a `shadow-cljs` ClojureScript browser REPL
from your editor and configuration.

For example, in Vim / Neovim with `fireplace.vim`
1. Open a `.cljs` file in the project to activate `fireplace.vim`
2. In normal mode, execute the `Piggieback` command with this project's running build id, `:app`:
    ```vim
    :Piggieback :app
    ```

#### Connecting to the browser REPL from a terminal

1. Connect to the `shadow-cljs` nREPL:
    ```sh
    lein repl :connect localhost:8777
    ```
    The REPL prompt, `shadow.user=>`, indicates that is a Clojure REPL, not ClojureScript.

2. In the REPL, switch the session to this project's running build id, `:app`:
    ```clj
    (shadow.cljs.devtools.api/nrepl-select :app)
    ```
    The REPL prompt changes to `cljs.user=>`, indicating that this is now a ClojureScript REPL.
3. See [`user.cljs`](dev/cljs/user.cljs) for symbols that are immediately accessible in the REPL
without needing to `require`.

### Running Tests

Build the app with the `prod` profile, start a temporary local web server, launch headless
Chrome/Chromium, run tests, and stop the web server:

```sh
npm install
npm run ci
```

Please be patient; it may take over 15 seconds to see any output, and over 25 seconds to complete.

Or, for auto-reload:
```sh
npm install
npm run watch
```

Then in another terminal:
```sh
karma start
```

### Running `shadow-cljs` Actions

See a list of [`shadow-cljs CLI`](https://shadow-cljs.github.io/docs/UsersGuide.html#_command_line)
actions:
```sh
npx shadow-cljs --help
```

Please be patient; it may take over 10 seconds to see any output. Also note that some actions shown
may not actually be supported, outputting "Unknown action." when run.

Run a shadow-cljs action on this project's build id (without the colon, just `app`):
```sh
npx shadow-cljs <action> app
```
### Debug Logging

The `debug?` variable in [`config.cljs`](src/cljs/bar/config.cljs) defaults to `true` in
[`dev`](#running-the-app) builds, and `false` in [`prod`](#production) builds.

Use `debug?` for logging or other tasks that should run only on `dev` builds:

```clj
(ns bar.example
  (:require [bar.config :as config])

(when config/debug?
  (println "This message will appear in the browser console only on dev builds."))
```

## Production

Build the app with the `prod` profile:

```sh
npm install
npm run release
```

Please be patient; it may take over 15 seconds to see any output, and over 30 seconds to complete.

The `resources/public/js/compiled` directory is created, containing the compiled `app.js` and
`manifest.edn` files.
