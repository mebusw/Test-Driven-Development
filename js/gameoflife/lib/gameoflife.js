/*
 * gameoflife
 * https://github.com/mebusw/Test-Driven-Development
 *
 * Copyright (c) 2012 mebusw
 * Licensed under the MIT license.
 */

var Game = function() {
    return {
        tick: function (grid) {
            if(grid.length === 1) {
                    return [0];
            }
            return [0, 0];
        }
    };
    
}