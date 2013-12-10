包子铺系列招式（KataBunStore）


招式一：将包子制作职责一分为二(Split Responsibilities)
庆丰包子一位老厨师老钱辞职创业开包子铺，制作销售庆丰风格的包子。因生意兴隆，老钱改为只负责拌馅(mix stuffing)、和面(knead into dough)和擀皮包馅（wrap bun）的工作(raw material making)，而新招聘一位厨师小曾，来负责上屉蒸熟（steam），装盘销售（dish out)的后续工作(follow-up work)。包子铺目前销售猪肉大葱（Green Onion and Pork bun)、猪肉三鲜(Sam Sun and Pork bun)和素三鲜(Su Sam Sun bun)包子，不久将销售新品种鲜虾(Shrimp bun)包子。试用代码来实现包子铺上述需求，并使得添加新品种鲜虾包子时，不需修改小曾的职责的代码，而仅修改老钱的职责的代码。
提示：可用Simple Factory。
参考测试1：顾客点了猪肉大葱包子，系统应该返回字符串“Mixed stuffing of Green Onion and Pork bun. Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.”
参考测试2：顾客点了猪肉三鲜包子，系统应该返回字符串“Mixed stuffing of Sam Sum and Pork bun. Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.”
参考测试3：顾客点了素三鲜包子，系统应该返回字符串“Mixed stuffing of Su Sam Sun bun. Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.”
参考测试4：顾客点了鲜虾包子，系统应该返回字符串“Mixed stuffing of Shrimp bun. Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.”


招式二：挖来两位厨师(Add Two New Flavors)
包子铺为拓展市场增加新口味，从狗不理包子挖来一位厨师老高，负责按照狗不理（GBL）的风格进行拌馅、和面和擀皮包馅的工作，而老钱则继续以前制作庆丰（QF）风格的包子工作，小曾则不管哪种风格的包子，一律上屉蒸熟，装盘销售。老高制作的狗不理包子的种类包括猪肉（Pork）、三鲜（Sam Sun）和酱肉（Sauced Pork）。今后还打算从四川韩包子（HBZ）再挖来一位厨师老何，进行韩包子风格的拌馅、和面和擀皮包馅的工作，并推出韩包子风格的鲜肉包子（Pork）。试着在招式一的代码的基础上重构代码，来实现上述包子铺的需求，并使得添加新的四川韩包子风格的包子时，不需修改老钱、小曾和老高的职责的代码，而仅仅新增老何的职责代码。
提示：可用Factory Method模式
参考测试1：顾客点了庆丰风格的猪肉大葱包子，系统应该返回字符串“Mixed stuffing of Green Onion and Pork bun in QF style. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.”
参考测试2：顾客点了狗不理风格的猪肉包子，系统应该返回字符串“Mixed stuffing of Pork bun in GBL style. Kneaded into dough in GBL style. Wrapped buns in GBL style. Steamed buns. Dished out buns.”
参考测试3：顾客点了四川韩包子风格的鲜肉包子，系统应该返回字符串“Mixed stuffing of Pork bun in HBZ style. Kneaded into dough in HBZ style. Wrapped buns in HBZ style. Steamed buns. Dished out buns.”




招式三：馅料与面粉直接由厂家供货(Get Raw Material From Factories)
过来一段时间，老高和老何相继辞职。老钱也发现直接从厂家采购原料成本更低，就改从庆丰、狗不理和四川韩包子厂家（Bun Factory）直接订购馅料和面粉，自己和小曾只负责擀皮包馅（prepare），上屉蒸熟（steam），装盘销售（dish out)的工作，制作的包子的风格和种类与以前相同。试着在招式二的代码的基础上重构代码，来实现上述包子铺的需求，并使得代码中能体现出馅料和面粉是由厂家来负责供应的。
提示：可结合使用Factory Method和Abstract Factory模式
参考测试1：顾客点了庆丰风格的猪肉大葱包子，系统应该返回字符串“Got mixed stuffing of Green Onion and Pork bun from QF Bun Factory. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.”
参考测试2：顾客点了狗不理风格的猪肉包子，系统应该返回字符串“Got mixed stuffing of Pork bun in GBL style. Kneaded into dough in GBL style. Wrapped buns in GBL style. Steamed buns. Dished out buns.”
参考测试3：顾客点了四川韩包子风格的鲜肉包子，系统应该返回字符串“Got mixed stuffing of Pork bun in HBZ style. Kneaded into dough in HBZ style. Wrapped buns in HBZ style. Steamed buns. Dished out buns.”
============

原创文章，转载请注明出处链接：http://blog.csdn.net/wubinben28/article/details/17231271 ，谢谢！