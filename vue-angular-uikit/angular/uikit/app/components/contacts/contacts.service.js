app.service('contactsService', function() {
	
	this.getContacts = function(){
		contactList = [ {
			pic: '../../shared-assets/img/01.jpg',
			name : 'Natasha Romanov',
			role: 'Black Widow',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com',
			ext: '456',
			country: 'ru',
			headquarter: 'Stark Building',
			status: 1,
			type: 'normal',
			skype: 'n.romanov'
		}, {
			pic: '../../shared-assets/img/02.jpg',
			name : 'Steve Rogers',
			role: 'Captain America',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/03.jpg',
			name : 'Tony Stark',
			role: 'Ironman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/04.jpg',
			name : 'Thor Odinson',
			role: 'Thor',
			phone: '1800-5698-123',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/05.jpg',
			name : 'Dr. Henry J. "Hank" Pym',
			role: 'Ant-Man',
			phone: '1800-555-742',
			address: 'Street 54 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/06.jpg',
			name : 'Bruce Banner',
			role: 'Hulk',
			phone: '9000-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/07.jpg',
			name : 'Clinton Francis Barton',
			role: 'Hawkeye',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/08.jpg',
			name : 'Pietro Maximoff',
			role: 'Quicksilver',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/09.jpg',
			name : 'Wanda Maximoff',
			role: 'Scarlet Witch',
			phone: '666-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/10.jpg',
			name : 'Victor Shade',
			role: 'Vision',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/11.jpg',
			name : 'Carol Susan Jane Danvers',
			role: 'Ms Marvel',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/12.jpg',
			name : 'Peter Benjamin Parker',
			role: 'Spiderman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		} 
		
		
		
		
		
		
		
		
		,{
			pic: '../../shared-assets/img/01.jpg',
			name : 'Natasha Romanov',
			role: 'Black Widow',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com',
			ext: '456',
			country: 'ru',
			headquarter: 'Stark Building',
			status: 1,
			type: 'normal',
			skype: 'n.romanov'
		}, {
			pic: '../../shared-assets/img/02.jpg',
			name : 'Steve Rogers',
			role: 'Captain America',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/03.jpg',
			name : 'Tony Stark',
			role: 'Ironman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/04.jpg',
			name : 'Thor Odinson',
			role: 'Thor',
			phone: '1800-5698-123',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/05.jpg',
			name : 'Dr. Henry J. "Hank" Pym',
			role: 'Ant-Man',
			phone: '1800-555-742',
			address: 'Street 54 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/06.jpg',
			name : 'Bruce Banner',
			role: 'Hulk',
			phone: '9000-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/07.jpg',
			name : 'Clinton Francis Barton',
			role: 'Hawkeye',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/08.jpg',
			name : 'Pietro Maximoff',
			role: 'Quicksilver',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/09.jpg',
			name : 'Wanda Maximoff',
			role: 'Scarlet Witch',
			phone: '666-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/10.jpg',
			name : 'Victor Shade',
			role: 'Vision',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/11.jpg',
			name : 'Carol Susan Jane Danvers',
			role: 'Ms Marvel',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/12.jpg',
			name : 'Peter Benjamin Parker',
			role: 'Spiderman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		},{
			pic: '../../shared-assets/img/01.jpg',
			name : 'Natasha Romanov',
			role: 'Black Widow',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com',
			ext: '456',
			country: 'ru',
			headquarter: 'Stark Building',
			status: 1,
			type: 'normal',
			skype: 'n.romanov'
		}, {
			pic: '../../shared-assets/img/02.jpg',
			name : 'Steve Rogers',
			role: 'Captain America',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/03.jpg',
			name : 'Tony Stark',
			role: 'Ironman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/04.jpg',
			name : 'Thor Odinson',
			role: 'Thor',
			phone: '1800-5698-123',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/05.jpg',
			name : 'Dr. Henry J. "Hank" Pym',
			role: 'Ant-Man',
			phone: '1800-555-742',
			address: 'Street 54 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/06.jpg',
			name : 'Bruce Banner',
			role: 'Hulk',
			phone: '9000-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/07.jpg',
			name : 'Clinton Francis Barton',
			role: 'Hawkeye',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/08.jpg',
			name : 'Pietro Maximoff',
			role: 'Quicksilver',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/09.jpg',
			name : 'Wanda Maximoff',
			role: 'Scarlet Witch',
			phone: '666-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/10.jpg',
			name : 'Victor Shade',
			role: 'Vision',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/11.jpg',
			name : 'Carol Susan Jane Danvers',
			role: 'Ms Marvel',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/12.jpg',
			name : 'Peter Benjamin Parker',
			role: 'Spiderman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		},{
			pic: '../../shared-assets/img/01.jpg',
			name : 'Natasha Romanov',
			role: 'Black Widow',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com',
			ext: '456',
			country: 'ru',
			headquarter: 'Stark Building',
			status: 1,
			type: 'normal',
			skype: 'n.romanov'
		}, {
			pic: '../../shared-assets/img/02.jpg',
			name : 'Steve Rogers',
			role: 'Captain America',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/03.jpg',
			name : 'Tony Stark',
			role: 'Ironman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/04.jpg',
			name : 'Thor Odinson',
			role: 'Thor',
			phone: '1800-5698-123',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/05.jpg',
			name : 'Dr. Henry J. "Hank" Pym',
			role: 'Ant-Man',
			phone: '1800-555-742',
			address: 'Street 54 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/06.jpg',
			name : 'Bruce Banner',
			role: 'Hulk',
			phone: '9000-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/07.jpg',
			name : 'Clinton Francis Barton',
			role: 'Hawkeye',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/08.jpg',
			name : 'Pietro Maximoff',
			role: 'Quicksilver',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/09.jpg',
			name : 'Wanda Maximoff',
			role: 'Scarlet Witch',
			phone: '666-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		}, {
			pic: '../../shared-assets/img/10.jpg',
			name : 'Victor Shade',
			role: 'Vision',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com'
		}, {
			pic: '../../shared-assets/img/11.jpg',
			name : 'Carol Susan Jane Danvers',
			role: 'Ms Marvel',
			phone: '1800-5698-742',
			address: 'Street 13 45 987',
			email: 'item2@shield.com'
		}, {
			pic: '../../shared-assets/img/12.jpg',
			name : 'Peter Benjamin Parker',
			role: 'Spiderman',
			phone: '1800-5698-743',
			address: 'Street 14 45 987',
			email: 'item3@shield.com'
		},{
			pic: '../../shared-assets/img/01.jpg',
			name : 'Natasha Romanov',
			role: 'Black Widow',
			phone: '1800-5698-741',
			address: 'Street 12 45 987',
			email: 'item1@shield.com',
			ext: '456',
			country: 'ru',
			headquarter: 'Stark Building',
			status: 1,
			type: 'normal',
			skype: 'n.romanov'
		}
		];
		
		return contactList;
	}
});