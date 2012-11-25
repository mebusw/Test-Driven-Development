/**
 * game of life https://github.com/mebusw/Test-Driven-Development
 * 
 * Copyright (c) 2012 mebusw Licensed under the MIT license.
 */

var Game = function() {
    return {
        tick : function(init_grid) {
            var len = init_grid[0].length;
            var result = this._initGrid(0, len);
            for ( var i = 1; i < len - 1; i++) {
                if (init_grid[0][i] === 1 && init_grid[0][i - 1] === 1 && init_grid[0][i + 1] === 1) {
                    result[0][i] = 1;
                }

            }
            return result;
        },

        _initGrid : function(elem, len) {
            var arr = [[]];
            for ( var i = 0; i < len; i++) {
                arr[0].push(elem);
            }
            return arr;
        },
    };

}