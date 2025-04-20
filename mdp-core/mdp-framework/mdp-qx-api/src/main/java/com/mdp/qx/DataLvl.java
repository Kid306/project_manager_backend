package com.mdp.qx;

public enum DataLvl {
	
	noDef(-1),forbiddenAll(0),myDept(1),subDept(2),p1(3),p2(4),branch(5),allowAll(6);
	 
	Integer lvl=-1;

	public static DataLvl getDataLvl(Integer dataLvl){
		if(dataLvl==null){
			return noDef;
		}
		if(dataLvl<=-1){
			return noDef;
		}else if(dataLvl<=0){
			return forbiddenAll;
		}else if(dataLvl<=1){
			return myDept;
		}else if(dataLvl<=2){
			return subDept;
		}else if(dataLvl<=3){
			return p1;
		}else if(dataLvl<=4){
			return p2;
		}else if(dataLvl<=5){
			return branch;
		}else if(dataLvl<=6){
			return allowAll;
		}else if(dataLvl>6){
			return allowAll;
		}
		return noDef;
	}

	public Integer compareToLvl(DataLvl dataLvl){
		if(dataLvl==null){
			return this.lvl.compareTo(noDef.getLvl());
		}else{
			return this.lvl.compareTo(dataLvl.getLvl());
		}
	}

	public Integer getLvl() {
		return lvl;
	}

	DataLvl(Integer lvl) {
		this.lvl=lvl;
	}


	public static void main(String[] args) {
		DataLvl dataLvl=DataLvl.getDataLvl(1);
	}
}
