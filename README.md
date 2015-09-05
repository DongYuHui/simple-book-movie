# 豆瓣书影(第三方个人作品)

这个开源应用源于我个人钟爱豆瓣的读书与电影两个功能，同时又无奈官方的应用臃肿繁杂，才试图自己开发的。想到可能有人会和我一样只用这些简单的功能，所以大家如果喜欢就最好来了。

我会尽量在自己能力范围内，将应用打造地完善易用，同时美观能够遵循 Material Design。但是豆瓣的 API 大家都知道的有名的简陋，所以做的不好的地方请原谅。

整个应用用到的开源框架有：Volley、[CircleImageView](https://github.com/hdodenhof/CircleImageView)。以及 Google 官方的 RecyclerView、CardView 和 Design 包。

---

下面是开发过程的一个简短的记录：

2015-9-3 因为一些摸不着头脑的问题，整整折腾了三天才把框架搭建起来。最后终于用理想的方式，即 NavigationView + TabLayout + ViewPager 的方式实现了应用的整体架构。

2015-9-4 完成了界面中列表 Item 的布局和数据封装，完成了用户授权登录的功能，以及获取用户的简洁信息到 NavigationView 的 Header 中。

2015-9-5 完成了电影部分的 RecyclerView 视图，实现下拉刷新，向下加载更多的功能。优化了 NavigationView 中的 UI 效果。