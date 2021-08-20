# v2.0.0:

* Fixed SleepFixer not starting on 1.13 and 1.14 servers (caused by having `api-version: 1.15`).
* Added phantom timer reset for 1.9+ servers - when a player sleeps, everyone in their world will have their phantom
  timer reset.
* Added `settings.yml` so you may adjust certain settings to your liking.
* Removed `/sleepfixer` command - it was unnecessary.
* SleepFixer now uses the in-built MicroLib library.
* Significantly improved the Wiki... by a long shot.
* Added the open-source license `GNU AGPL v3.0` to the project.
* Removed the useless (as of current) compatibility checker, and added support for 1.17 servers.
* General improvements across the project. Too many to list here. :)

# v1.2.0:

* Improvement | Plugin now supports 1.16.x too.
* Improvement | Plugin is compiled in Spigot 1.16.1.
* Improvement | Handful of message changes in the commands and on startup.
* Improvement | Updated libraries.
* Improvement | Startup compatibility checker now checks a list of supported versions instead of just one supported
  version.
* Improvement | Added '1.16' to the startup compatibility checker's supported versions list.