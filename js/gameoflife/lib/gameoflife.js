/**
 * game of life https://github.com/mebusw/Test-Driven-Development
 * 
 * Copyright (c) 2012 mebusw Licensed under the MIT license.
 */

var Game = function() {
    return {
        tick : function(grid) {
            this.grid = grid;
            var len = grid[0].length;
            var result = this._initGrid(0, len);
            // for ( var i = 1; i < len - 1; i++) {
            // if (init_grid[0][i] === 1 && init_grid[0][i - 1] === 1 &&
            // init_grid[0][i + 1]
            // === 1) {
            // result[0][i] = 1;
            // }
            //
            // }
            if (this._isLeftAlive() && this._isAlive() && this._isRightAlive()) {
                result[0][1] = 1;
            }
            return result;
        },

        _initGrid : function(elem, len) {
            var arr = [ [] ];
            for ( var i = 0; i < len; i++) {
                arr[0].push(elem);
            }
            return arr;
        },

        _isAlive : function() {
            return this.grid[0][1] === 1;
        },

        _isLeftAlive : function() {
            return this.grid[0][0] === 1;
        },

        _isRightAlive : function() {
            return this.grid[0][2] === 1;
        },
    };

}