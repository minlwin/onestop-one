# Prepare Database

<code>
	create database balances;	
	create user 'balances'@'localhost' identified by 'balances';
	grand all privileges on balances.* to 'balances'@'localhost';
</code>