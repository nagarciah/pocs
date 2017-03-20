delete from transition;
delete from node_attributes;
delete from node;
delete from node_type;
delete from flow;

insert into node_type(id, description, friendly_name, model_class_name, node_usage) 
values (1, 'Punto de entrada al flujo', 'Inicio', 'com.nagarciah.trainning.statemachine.nodes.StartNode', 'Indica la transición hacia el primer nodo del flujo');
insert into node_type(id, description, friendly_name, model_class_name, node_usage) 
values (2, 'Presenta un formulario USSD', 'Formulario USSD', 'com.nagarciah.trainning.statemachine.nodes.FormNode', 'label: indica el texto a presentar al usuario; input: contiene el dato ingresado por el usuario');

insert into flow(id, name) values (1, 'Flujo simple request-response');

insert into node(id, description, name, node_processor_class_name, node_type_id, flow_id)
values (1, 'Nodo inicial', 'Inicio', 'com.nagarciah.trainning.statemachine.nodes.StartNodeProcessor', 1, 1);
insert into node(id, description, name, node_processor_class_name, node_type_id, flow_id)
values (2, 'Genera formulario', 'Pregunta cedula', 'com.nagarciah.trainning.statemachine.nodes.FormNodeProcessor', 1, 1);


insert into node_attributes(node_id, attributes, attributes_key)
values (1, 'Ingrese su numero de cedula', 'label');

insert into transition(id, flow_id, evaluator, from_node_id, to_node_id)
values(1, 1, 'com.etc', 1, 2);
delete from transition;
delete from node_attributes;
delete from node;
delete from flow;
delete from node_type;

insert into node_type(id, description, friendly_name, model_class_name, node_usage) 
values (1, 'Punto de entrada al flujo', 'Inicio', 'com.nagarciah.trainning.statemachine.nodes.StartNode', 'Indica la transición hacia el primer nodo del flujo');
insert into node_type(id, description, friendly_name, model_class_name, node_usage) 
values (2, 'Presenta un formulario USSD', 'Formulario USSD', 'com.nagarciah.trainning.statemachine.nodes.FormNode', 'label: indica el texto a presentar al usuario; input: contiene el dato ingresado por el usuario');

insert into flow(id, name) values (1, 'Flujo simple request-response');

insert into node(id, description, name, node_processor_class_name, node_type_id, flow_id)
values (1, 'Nodo inicial', 'Inicio', 'com.nagarciah.trainning.statemachine.nodes.StartNodeProcessor', 1, 1);
insert into node(id, description, name, node_processor_class_name, node_type_id, flow_id)
values (2, 'Genera formulario', 'Pregunta cedula', 'com.nagarciah.trainning.statemachine.nodes.FormNodeProcessor', 1, 1);


insert into node_attributes(node_id, attributes, attributes_key)
values (1, 'Ingrese su numero de cedula', 'label');

insert into transition(id, flow_id, class_name, from_node_id, to_node_id)
values(1, 1, 'com.nagarciah.trainning.statemachine.nodes.DummyCondition', 1, 2);