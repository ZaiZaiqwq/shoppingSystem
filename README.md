# shoppingSystem
## 这是一个java写的购物系统
程序运行后首先会进入登录界面，有四个选项。输入1进入管理员登录选项，输入2进入客户登录选项，输入3进入客户注册选项，输入4退出程序。输入其他输出无效输入，可重新输入。

> 1.在管理员登录中根据提示输入用户名（admin）和密码（默认为ynuadmin），登陆成功后会进入管理员菜单界面。只有当用户名和密码都正确时才登录，否则返回初始登录界面。

> 进入到管理员菜单界面有四个选项。输入1进入密码管理选项，输入2进入客户管理选项，输入3进入商品管理选项，输入4退出登录，返回初始登录界面。输入其他输出无效输入，可重新输入。
***
> > 1.1.进入到密码管理界面有三个选项。输入1进入修改密码选项，输入2进入重置客户密码选项，输入3返回管理员菜单界面。输入其他输出无效输入，可重新输入。
***
> > > 1.1.1.在管理员修改密码中，根据提示输入新的密码，显示密码修改成功，返回密码管理界面。
***
> > > 1.1.2.在重置客户密码中，根据提示输入想要重置密码的客户的用户名，如果找到该客户，则提示密码重置成功，若该用户不存在，则提示找不到该用户，密码重置失败。默认重置的密码为“52Cxk!789”。
***
> > 1.2.进入客户管理界面有4个选项，输入1进入列出所有客户信息选项，输入2进入删除客户信息选项。输入3进入查询客户信息选项，输入4返回管理员菜单界面。输入其他输出无效输入，可重新输入。
***
> > > 1.2.1.在列出所有客户信息中，会进入所有客户信息界面，打印显示所有客户的相关信息，若没有任何用户，则输出提示，按<Enter>返回客户管理界面。
***
> > > 1.2.2.在删除客户信息中，根据提示输入想要删除的客户的用户名，如果找到该用户，则会提示删除后无法撤销，输入1继续执行删除操作，提示删除成功并返回客户管理界面；输入0（或其他）取消删除操作，返回客户管理界面。如果该用户不在，输入用户不存在，删除失败，并返回客户管理界面。
***
> > > 1.2.3.在查询客户信息界面中有4个选项，输入1进入根据ID查询，输入2进入根据用户名查询，输入3进入列出所有用户信息，输入4返回。输入其他输出无效输入，可重新输入。
***
> > > > 1.2.3.1.在根据ID查询客户信息中，根据提示输入想要查询的客户的id，如果找到该用户，则输出该用户的相关信息，按<Enter>返回查询客户信息界面；如果该用户不存在，输出未找到该用户并返回查询客户信息界面。
***
> > > > 1.2.3.2.在根据用户名查询客户信息中，根据提示输入想要查询的客户的用户名，如果找到该用户，则输出该用户的相关信息，按<Enter>返回查询客户信息界面；如果该用户不存在，输出未找到该用户并返回查询客户信息界面。
***
> > > > 1.2.3.3.列出所有客户信息同上文所述。
***
> > 1.3.进入到商品信息管理有6个选项，输入1进入列出所有商品信息，输入2进入添加商品信息，输入3进入修改商品信息，输入4进入删除商品信息，输入5进入查询商品信息，输入6返回管理员菜单界面。输入其他输出无效输入，可重新输入。
***
> > > 1.3.1.在列出所有商品信息中，会进入所有商品信息界面，打印显示所有商品的相关信息，若没有任何商品，则输出提示，按<Enter>返回商品管理界面。
***
> > > 1.3.2.在添加商品信息中，根据提示输入新商品的id，名称，生厂商，生产日期（yyyy.MM.dd），型号，进货价（有理数），单价（有理数），数量（正整数），和销量（正整数），输入的信息中均不能包含空格，如果输入的信息不符合要求，会提示输出无效输入并要求重新输入。当所有输入均符合要求时，添加成功，返回商品管理界面。
***
> > > 1.3.3.在修改商品信息中，根据提示输入想要修改商品的id，如果该商品不存在，输出提示并放回商品管理界面。若找到该商品，则会进入修改商品信息界面，输入1修改名称，输入2修改生产商，输入3修改生产日期，输入4修改型号，输入5修改进货价，输入6修改单价，输入7修改数量，输入8修改销售量，输入9将修改的信息保存，输入10确认后返回商品管理界面。输入其他输出无效输入，可重新输入。根据提示输入要修改的值，要求同上所述，若不符合要求会输出提示并返回修改商品信息界面。若输入符合要求则会进行修改。修改完所有的信息后点击保存输出提示修改成功并返回商品管理界面（注意：修改的信息只有保存后才会在系统中真正修改，若修改后不保存直接返回则不会修改）。
***
> > > 1.3.4.在删除商品信息中，根据提示输入想要删除的商品的id，如果找到该商品，则提示删除操作不可撤销，输入1继续删除操作，删除成功并返回商品管理界面；输入0（或其他）取消删除操作并返回商品管理界面。若该商品不存在，输出提示后返回商品管理界面。
***
> > > 1.3.5.在查询商品信息中，根据提示输入想要查询的商品的id，如果找到该商品，则输出该商品的相关信息，按<Enter>返回商品信息管理界面；若该商品不存在，输出提示并返回商品信息管理界面。
***
> 2.在客户登录中根据提示输入用户名和密码，登陆成功后会进入客户菜单界面。只有当用户名和密码匹配才登录，否则返回初始登录界面。

> 进入到客户菜单有3个选项，输入1进入密码管理选项，输入2进入购物管理选项，输入3退出登录，返回初始登录界面。输入其他输出无效输入，可重新输入。
***
> > 2.1.进入客户密码管理界面有三个选项，输入1进入修改密码选项，输入2进入重置密码选项，输入3返回客户菜单。输入其他输出无效输入，可重新输入。
***
> > > 2.1.1.在客户修改密码中，根据提示输入新的密码，格式要求与上述相同，若不符合要求会输出提示并返回密码管理界面。若符合要求，则修改成功，返回密码管理界面。
***
> > > 2.1.2.在客户重置密码中，根据提示输入自己的用户名和邮箱，若用户名或邮箱错误，会输出提示并返回密码管理界面。若输入的用户名和邮箱均正确，重置密码成功，新生成的随机密码将会发送到客户的邮箱中。
***
> > 2.2.进入购物管理界面会先输出展示当前客户的购物车信息，有7个选项，输入1进入将商品加入购物车选项，输入2进入将商品移出购物车选项，输入3进入修改购物车中商品数量选项，输入4进入支付选项，输入5进入查看购物记录选项，输入6进入查看热门商品清单选项，输入7返回客户菜单。输入其他输出无效输入，可重新输入。
***
> > > 2.2.1.在将商品加入购物车中，会先输出展示当前的所有商品信息，如果当时暂且没有任何商品，输出提示并放回购物管理界面。根据提示输入想要加入购物车的商品的id，如果购物车中已经存在该商品，则提示输出并返回购物管理界面。若购物车中不存在该商品，且该商品存在，则根据提示输入加入购物车的数量（正整数），如果输入不符合要求或大于目前存货，输出提示并返回购物管理界面。若输入的数量合法，则加入购物车成功，返回购物管理界面。若该商品不存在，则输出提示并返回购物管理界面。
***
> > > 2.2.2.在将商品移出购物车中，根据提示输入想要删除的商品的id，如果该商品不在购物车中，则输出提示并返回购物管理界面，若在购物车中找到该商品，则提示删除操作无法撤销，输入1继续删除操作，删除成功并返回购物管理界面，输入0（或其他）取消删除操作，返回购物管理界面。
***
> > > 2.2.3.在修改购物车中商品数量中，根据提示输入想要修改数量的商品的id，如果购物车中不存在该商品，则输出提示并返回购物管理界面，如果在购物车中找到该商品，并且该商品仍然存在，则输入想要修改的数量，要求与上述相同，若不符合要求则输出提示并返回购物管理函数。若新输入的数量小于零，则将其从购物车中删除，修改成功，返回购物管理函数。若该商品已下架，则输出提示后将该商品移出购物车并返回购物管理函数。
***
> > > 2.2.4.在支付选项中，如果购物车为空，输出提示并返回购物管理界面。如果购物车中存在已下架的商品或购物车中商品的数量大于存货，输出提示并返回购物管理界面。如果购物车中的商品均有效，则会输出显示总金额，进入支付界面，有四个选项，输入1选择支付宝支付，输入2选择微信支付，输入3选择银行卡支付，输入4取消支付并返回购物管理函数。入其他输出无效输入，返回购物管理函数。根据提示输入支付密码，支付成功。
***
> > > 2.2.5.在查看购物历史中，会进入购物历史界面，输出显示之前购买过的所有商品的信息及购买时间，若未购买过任何商品，则会输出暂无购物历史。按<Enter>返回购物管理界面。
***
> > > 2.2.6.在查看热销商品中，会进入热销商品清单界面，输出显示正在热销的产品的信息（默认为销量大于等于1000），若无销量大于1000的商品，则会输出暂无热销商品。按<Enter>返回购物管理界面。
***
> 3.在客户注册中根据提示输入想要注册的用户名（不少于5个字符，不能包含空格），密码（多于8个字符，并且至少包含一个大写字母，一个小写字母，一个数字和一个标点符号，不能包含空格），邮箱和手机号。若输入的信息不符合要求，则会弹出相应提示并返回初始登录界面。若输入的信息全部符合要求，则开始注册。若弹出“The username already exists. Registration failed.”则表示用户名已存在，注册失败，返回初始登录界面。若用户名不冲突，则注册成功，弹出相应提示并返回初始登录界面。
