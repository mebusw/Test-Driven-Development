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
            this.yLen = grid[0].length;
            this.xLen = grid.length;

            this._initGrid();

            for ( var col = 0; col < this.yLen; col++) {
                for ( var row = 0; row < this.xLen; row++) {
                    if (this._isAlive(row, col) && this._has2Or3AliveNeighbors(row, col)) {
                        this._setAlive(row, col);
                    }
                    if (this.xLen >= 2 && this._isDead(row, col) && this._has3AliveNeighbors(row, col)) {
                        this._setAlive(row, col);
                    }
                }
            }

            return this.nextGrid;
        },

        _initGrid : function() {
            this.nextGrid = [];
            for ( var i = 0; i < this.xLen; i++) {
                this.nextGrid[i] = [];
                for ( var j = 0; j < this.yLen; j++) {
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
            return j < this.yLen - 1 && this.grid[i][j + 1] === 1;
        },

        _isUpAlive : function(i, j) {
            return i > 0 && this.grid[i - 1][j] === 1;
        },

        _isDownAlive : function(i, j) {
            return i < this.xLen - 1 && this.grid[i + 1][j] === 1;
        },

        _has2Or3AliveNeighbors : function(i, j) {
            var count = this._countAliveNeighbors(i, j);
            return count === 2 || count === 3;
        },

        _has3AliveNeighbors : function(i, j) {
            return this._countAliveNeighbors(i, j) === 3;
        },

        _countAliveNeighbors : function(i, j) {
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
            return count;
        }
    };

};