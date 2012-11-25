/**
 * game of life https://github.com/mebusw/Test-Driven-Development
 * 
 * Copyright (c) 2012 mebusw Licensed under the MIT license.
 */

var Game = function() {
    return {
        tick : function(grid) {
            this.grid = grid;
            this._initGrid(0, grid[0].length);

            for ( var i = 0; i < grid[0].length; i++)
                if (this._isAlive(i) && this._has2AliveNeighboors(i)) {
                    this._setAlive(i);
                }
            return this.nextGrid;
        },

        _initGrid : function(elem, len) {
            this.nextGrid = [ [] ];
            for ( var i = 0; i < len; i++) {
                this.nextGrid[0].push(elem);
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
            return i < this.grid[0].length - 1 && this.grid[0][i + 1] === 1;
        },

        _has2AliveNeighboors : function(i) {
            return this._isLeftAlive(i) && this._isRightAlive(i);

        },
    };

}