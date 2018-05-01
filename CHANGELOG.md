# Changelog
## Unreleased
### Changes
- Refactorized part of `AppraisalHelper` `getWorstMargins` method to
the new `getList` method in the newly created `ItemListHelper` class
- `getWorstMargins` is now called `getEvepraisalJson` and returns a
String
- `MainWindow` now handles `getEvepraisalJson` IOException exceptions.
- Renamed `AppraisalHelper` `aHInstance` to `appraisalHelper`.
- Renamed `JTableHelper` `jTInstance` to `jTableHelper`.

## 0.2.2 - 2018-04-28
### Fixed
- Regression from 0.2.1 where a check for showBuybackList was missing making half
of the list show in the other list too.

## 0.2.1 - 2018-04-27
### Fixed
- Correctly detecting if it will go to the buyback list or not by fixing the
`showBuybackList` value which was reversed.
- Calculating the correct margin (clearly we don't want to know the 12000% of a
buy price when inputting 120 as margin!)

## 0.2.0 - 2018-04-27
### Added
- File menu and Exit menu item
- Evepraisal parameters (market and price percent), a sell-buy margin
percent parameter and select between showing the item list to sell via sell
orders (default) and items to use buyback or instasell.

### Fixed
- Main window title as it was using a previous title I though for this project
- Increase width for the top labels as they were trimmed in Java default style

## 0.1.0 - 2018-04-26
- First release!
