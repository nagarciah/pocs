'use strict';

describe('Todo list', function(){
	// A ejecutarse antes de cada test (it)
	beforeEach(function(){
		module('todo');
	});
	
	var scope = {};
	
	beforeEach(function(){
		inject(function($controller){
			$controller('TodoController', {$scope: scope});
		});
	});
	
	// test case o assert
	it('Should define a list of objects', function(){
		expect(scope.list).toBeDefined();
	});
	
	it('Should have first list item', function(){
		expect(scope.list[0]).toEqual('test');
	});
	
	it('Should have first list item', function(){
		expect(scope.list[1]).toEqual('execute');
	});
	
	it('Should have first list item', function(){
		expect(scope.list[2]).toEqual('refactor');
	});

	
	// Nuevo test suite para probar el metodo para agregar tareas
	describe('when using a todo-list', function(){
		beforeEach(function(){
			scope.add('repeat');
		});
		
		it('Should add an item to the end of the list', function(){
			var lastItemIndex = scope.list.length - 1;
			expect(scope.list[lastItemIndex]).toEqual('repeat');
		});
	});
	
})


/*
describe('Descripcion del test suite', function(){
	// A ejecutarse antes de cada test (it)
	beforeEach(function(){

	});
	
	// test case o assert
	it('Descripción del test', function(){
		
	});
})*/