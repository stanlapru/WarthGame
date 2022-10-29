# Warth

This is a private repository for the WIP very early Warth strategy online game, made with LibGDX and open-source libraries.
If you can see this, you are either a tester, translator or a co-developer. 
I thank you for your assistance.

A boilerplate notice after project generation is left below in case it's needed later.

Roadmap:  
:white_check_mark: Options(SharedStorage),  
:white_check_mark: GUI,  
:arrow_right: Translations,  
:arrow_right: Networking,  
:arrow_right: Small map,  
:white_large_square: Mechanics/Features,  
:white_large_square: Playtesting,  
:white_large_square: Release,  
:white_large_square: a 1$ advertising campaign. 

Estimated time is about 1 year.

## LibGDX Boilerplate

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/tommyettinger/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

### Gradle

This project uses [Gradle](http://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `android:lint`: performs Android project validation.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `server:run`: runs the server application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
