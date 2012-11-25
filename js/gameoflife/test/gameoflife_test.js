/**
 * The universe of the Game of Life is an infinite two-dimensional orthogonal
 * grid of square cells, each of which is in one of two possible states, alive
 * or dead. Every cell interacts with its eight neighbours, which are the cells
 * that are horizontally, vertically, or diagonally adjacent. At each step in
 * time, the following transitions occur:
 * 
 * Any live cell with fewer than two live neighbours dies, as if caused by
 * under-population.
 * 
 * Any live cell with two or three live neighbours lives on to the next
 * generation.
 * 
 * Any live cell with more than three live neighbours dies, as if by
 * overcrowding.
 * 
 * Any dead cell with exactly three live neighbours becomes a live cell, as if
 * by reproduction.
 * 
 * The initial pattern constitutes the seed of the system. The first generation
 * is created by applying the above rules simultaneously to every cell in the
 * seed-births and deaths occur simultaneously, and the discrete moment at which
 * this happens is sometimes called a tick (in other words, each generation is a
 * pure function of the preceding one). The rules continue to be applied
 * repeatedly to create further generations.
 * 
 * 
 */

describe("one cell", function() {
    var game;
    beforeEach(function() {
        game = new Game();
    });

    it("only one cell should die", function() {
        expect(game.tick([ [ 0 ] ])).toEqual([ [ 0 ] ]);
        expect(game.tick([ [ 1 ] ])).toEqual([ [ 0 ] ]);
    });

});

describe("two cell", function() {
    var game;
    beforeEach(function() {
        game = new Game();
    });

    it("two cells should die", function() {
        expect(game.tick([ [ 0, 0 ] ])).toEqual([ [ 0, 0 ] ]);
        expect(game.tick([ [ 1, 0 ] ])).toEqual([ [ 0, 0 ] ]);
        expect(game.tick([ [ 0, 1 ] ])).toEqual([ [ 0, 0 ] ]);
    });

});

describe("three cell", function() {
    var game;
    beforeEach(function() {
        game = new Game();
    });

    it("cell with less than two alive neignboor will die", function() {
        expect(game.tick([ [ 0, 0, 0 ] ])).toEqual([ [ 0, 0, 0 ] ]);
        expect(game.tick([ [ 0, 1, 0 ] ])).toEqual([ [ 0, 0, 0 ] ]);
        expect(game.tick([ [ 1, 0, 0 ] ])).toEqual([ [ 0, 0, 0 ] ]);
        expect(game.tick([ [ 0, 0, 1 ] ])).toEqual([ [ 0, 0, 0 ] ]);
        expect(game.tick([ [ 1, 1, 0 ] ])).toEqual([ [ 0, 0, 0 ] ]);
        expect(game.tick([ [ 0, 1, 1 ] ])).toEqual([ [ 0, 0, 0 ] ]);
    });
    it("dead cell with twp alive neighboor will dead", function() {
        expect(game.tick([ [ 1, 0, 1 ] ])).toEqual([ [ 0, 0, 0 ] ]);
    });

    it("alive cell with twp alive neighboor will live", function() {
        expect(game.tick([ [ 1, 1, 1 ] ])).toEqual([ [ 0, 1, 0 ] ]);
    });

});

describe("four cell", function() {
    var game;
    beforeEach(function() {
        game = new Game();
    });

    it("one or two cells alive, but with less than two alive neignboor will die", function() {
        expect(game.tick([ [ 0, 0, 0, 0 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
        expect(game.tick([ [ 0, 1, 0, 0 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
        expect(game.tick([ [ 0, 0, 1, 0 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
        expect(game.tick([ [ 1, 0, 0, 1 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
        expect(game.tick([ [ 1, 1, 0, 0 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
        expect(game.tick([ [ 1, 0, 1, 0 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
    });

    it("three alive cells, but with less than two alive neignboor will die", function() {
        expect(game.tick([ [ 1, 1, 0, 1 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
        expect(game.tick([ [ 1, 0, 1, 1 ] ])).toEqual([ [ 0, 0, 0, 0 ] ]);
    });

    it("three more alive cells, with two alive neignboor will die", function() {
        expect(game.tick([ [ 1, 1, 1, 0 ] ])).toEqual([ [ 0, 1, 0, 0 ] ]);
        expect(game.tick([ [ 0, 1, 1, 1 ] ])).toEqual([ [ 0, 0, 1, 0 ] ]);
        expect(game.tick([ [ 1, 1, 1, 1 ] ])).toEqual([ [ 0, 1, 1, 0 ] ]);
    });

});
