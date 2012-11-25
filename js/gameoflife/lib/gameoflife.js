/**
 * game of life https://github.com/mebusw/Test-Driven-Development
 * 
 * Copyright (c) 2012 mebusw Licensed under the MIT license.
 */

var Game = function() {
    return {
        tick : function(init_grid) {
            var grid_line_0 = init_grid[0];
            var len = grid_line_0.length;
            var result = [this._arrayGen(0, len)];
            for ( var i = 1; i < len - 1; i++) {
                if (grid_line_0[i] === 1 && grid_line_0[i - 1] === 1 && grid_line_0[i + 1] === 1) {
                    result[0][i] = 1;
                }

            }
            return result;
        },

        _arrayGen : function(elem, len) {
            var arr = [];
            for ( var i = 0; i < len; i++) {
                arr.push(elem);
            }
            return arr;
        },
    };

}