# TicTacToe
To run: java Tournament [round count] [render target: console/none]
[player: human/clever/whatever/...]  [player: human/clever/whatever/...]

The Clever Player Chooses cells at random, until he finds an cell with
his mark. Then he checks all the surrounding cells and looks for empty cells.
the clever player will always choose the first empty cell he finds.
If there are no empty cells in the surrounding, he "gives up" and chooses an cell at random
(using the playTurn() method of the whatever player).

The SnartyPamts uses the same strategy like the clever player,
but he doesn't give up until he finds an empty cell near a cell with his mark.

The whatever player chooses a cell at random.
He chooses random indexes for the row and column to mark, using the advantage of the
putMark() method, which returns true or false if the mark was put on the game board.
He doesn't stop until he gets what he wants and chooses cells at random,
until the cell is a blank one.

500 rounds between whatever and clever
clever won 324
whatever won 176
draw 0

500 rounds between whatever and snartypamts
snartypamts won 476
whatever won 24
draw 0

500 rounds between clever and snartypamts
snartypamts won 416
clever won 84
draw 0

10000 rounds between whatever and whatever
whatever1 won 4796
whatever2 won 4924
draw 100
