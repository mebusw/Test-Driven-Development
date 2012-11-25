/**
 * game of life https://github.com/mebusw/Test-Driven-Development
 * 
 * Copyright (c) 2012 mebusw Licensed under the MIT license.
 */

function arrays_equal(a, b) {
    return a[0].length === b[0].length && a.toString() === b.toString();
}

var Game = function() {
    return {
        tick : function(grid) {
            this.grid = grid;
            this.xLen = grid[0].length;
            this.yLen = grid.length;

            this._initGrid();

            for ( var i = 0; i < this.xLen; i++)
                if (this._isAlive(i) && this._has2AliveNeighboors(i)) {
                    this._setAlive(i);
                }
            return this.nextGrid;
        },

        _initGrid : function() {
            this.nextGrid = [];
            if (this.grid.length >= 2) {
                for ( var i = 0; i < this.yLen; i++) {
                    this.nextGrid.push([ 0 ]);
                }

            } else {
                this.nextGrid.push([]);
                for ( var i = 0; i < this.xLen; i++) {
                    this.nextGrid[0].push(0);
                }
            }
            return this.nextGrid;
        },

        _setAlive : function(i) {
            this.nextGrid[0][i] = 1;
        },
        _isAlive : function(i) {
            return this.grid[0][i] === 1;
        },

        _isLeftAlive : function(i) {
            return i > 0 && this.grid[0][i - 1] === 1;
        },

        _isRightAlive : function(i) {
            return i < this.xLen - 1 && this.grid[0][i + 1] === 1;
        },

        _has2AliveNeighboors : function(i) {
            return this._isLeftAlive(i) && this._isRightAlive(i);

        },
    };

}