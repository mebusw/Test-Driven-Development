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

            if (this.xLen > 1 || this.yLen > 1) {
                for ( var j = 0; j < this.xLen; j++) {
                    if (this._isAlive(0, j) && this._has2AliveNeighboors(0, j)) {
                        this._setAlive(0, j);
                    }
                }
                if (this.yLen >= 2 && this._isAlive(1, 0) && this._isUpAlive(1, 0) && this._isDownAlive(1, 0)) {
                    this._setAlive(1, 0);
                }
            }

            return this.nextGrid;
        },

        _initGrid : function() {
            this.nextGrid = [];          
            for ( var i = 0; i < this.yLen; i++) {
                this.nextGrid[i] = [];
                for ( var j = 0; j < this.xLen; j++) {
                    this.nextGrid[i].push(0);
                }

            }
            return this.nextGrid;
        },

        _setAlive : function(i, j) {
            this.nextGrid[i][j] = 1;
        },

        _isAlive : function(i, j) {
            return this.grid[i][j] === 1;
        },

        _isLeftAlive : function(i, j) {
            return j > 0 && this.grid[i][j - 1] === 1;
        },

        _isRightAlive : function(i, j) {
            return j < this.xLen - 1 && this.grid[i][j + 1] === 1;
        },

        _isUpAlive : function(i, j) {
            return i > 0 && this.grid[i - 1][j] === 1;
        },

        _isDownAlive : function(i, j) {
            return i < this.yLen - 1 && this.grid[i + 1][j] === 1;
        },

        _has2AliveNeighboors : function(i, j) {
            return this._isLeftAlive(i, j) && this._isRightAlive(i, j);

        },
    };

}