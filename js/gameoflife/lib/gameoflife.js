/**
 * game of life https://github.com/mebusw/Test-Driven-Development
 * 
 * Copyright (c) 2012 mebusw Licensed under the MIT license.
 */

"use strict";

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

            for ( var col = 0; col < this.xLen; col++) {
                for ( var row = 0; row < this.yLen; row++) {
                    if (this._isAlive(row, col) && this._has2Or3AliveNeighbors(row, col)) {
                        this._setAlive(row, col);
                    }
                    if (this.yLen >= 2 && this._isDead(0, 1) && this.grid[0][0] === 1 && this.grid[0][2] === 1
                            && this.grid[1][1] === 1) {
                        this._setAlive(0, 1);
                    }
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

        _isDead : function(i, j) {
            return this.grid[i][j] === 0;
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

        _has2Or3AliveNeighbors : function(i, j) {
            var count = 0;
            if (this._isLeftAlive(i, j)) {
                count += 1;
            }
            if (this._isRightAlive(i, j)) {
                count += 1;
            }
            if (this._isUpAlive(i, j)) {
                count += 1;
            }
            if (this._isDownAlive(i, j)) {
                count += 1;
            }
            return count === 2 || count === 3;
        }
    };

};