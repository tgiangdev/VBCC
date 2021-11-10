export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        path: '/user',
        routes: [
          {
            name: 'login',
            path: '/user/login',
            component: './user/Login',
          },
        ],
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
    hideInMenu: true
  },
  {
    path: '/quan-ly-van-bang',
    name: 'quan-ly-van-bang',
    icon: 'crown',
    routes: [
      {
        path: '/quan-ly-van-bang/danh-sach-tot-nghiep',
        name: 'danh-sach-tot-nghiep',
        icon: 'smile',
        component: './quan-ly-van-bang/danh-sach-tot-nghiep',
      },
      {
        path: '/quan-ly-van-bang/phoi-van-bang',
        name: 'phoi-van-bang',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-van-bang/so-goc',
        name: 'so-goc',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-van-bang/cap-phat-van-bang-goc',
        name: 'cap-phat-van-bang-goc',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-van-bang/cap-phat-ban-sao',
        name: 'phoi-van-bang',
        icon: 'smile',
        component: './Welcome',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/thong-bao',
    name: 'thong-bao',
    icon: 'crown',
    routes: [
      {
        path: '/thong-bao/danh-sach-thong-bao',
        name: 'danh-sach-thong-bao',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/thong-bao/gui-thong-bao',
        name: 'gui-thong-bao',
        icon: 'smile',
        component: './Welcome',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/quan-ly-danh-muc',
    name: 'quan-ly-danh-muc',
    icon: 'crown',
    routes: [
      {
        path: '/quan-ly-danh-muc/khoa-hoc',
        name: 'khoa-hoc',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-danh-muc/don-vi',
        name: 'don-vi',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-danh-muc/dan-toc',
        name: 'dan-toc',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-danh-muc/don-vi-hanh-chinh',
        name: 'don-vi-hanh-chinh',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-danh-muc/xep-loai-tot-nghiep',
        name: 'xep-loai-tot-nghiep',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-danh-muc/hinh-thuc-dao-tao',
        name: 'hinh-thuc-dao-tao',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-danh-muc/van-ban-mau',
        name: 'van-ban-mau',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-danh-muc/mau-phoi',
        name: 'mau-phoi',
        icon: 'smile',
        component: './Welcome',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/bao-cao-thong-ke',
    name: 'bao-cao-thong-ke',
    icon: 'crown',
    routes: [
      {
        path: '/bao-cao-thong-ke/bao-cao-hoc-sinh-tot-nghiep',
        name: 'bao-cao-hoc-sinh-tot-nghiep',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/bao-cao-thong-ke/bao-cao-tinh-trang-su-dung-phoi',
        name: 'bao-cao-tinh-trang-su-dung-phoi',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/bao-cao-thong-ke/bao-cao-cap-phat-van-bang-goc',
        name: 'bao-cao-cap-phat-van-bang-goc',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/bao-cao-thong-ke/lich-su-truy-cap',
        name: 'lich-su-truy-cap',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/bao-cao-thong-ke/lich-su-khai-thac',
        name: 'lich-su-khai-thac',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/bao-cao-thong-ke/thong-ke-tra-cuu',
        name: 'thong-ke-tra-cuu',
        icon: 'smile',
        component: './Welcome',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/quan-ly-nhom-va-tai-khoan',
    name: 'quan-ly-nhom-va-tai-khoan',
    icon: 'crown',
    routes: [
      {
        path: '/quan-ly-nhom-va-tai-khoan/nhom-nguoi-dung',
        name: 'nhom-nguoi-dung',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-nhom-va-tai-khoan/nguoi-dung',
        name: 'nguoi-dung',
        icon: 'smile', 
        component: './quan-ly-nhom-va-tai-khoan/nguoi-dung',
      },
      {
        path: '/quan-ly-nhom-va-tai-khoan/quyen-thao-tac',
        name: 'quyen-thao-tac',
        icon: 'smile',
        component: './Welcome',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/quan-ly-he-thong',
    name: 'quan-ly-he-thong',
    icon: 'crown',
    routes: [
      {
        path: '/quan-ly-he-thong/cau-hinh-he-thong',
        name: 'cau-hinh-he-thong',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-he-thong/du-lieu-mac-dinh',
        name: 'du-lieu-mac-dinh',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-he-thong/sao-luu-du-lieu',
        name: 'sao-luu-du-lieu',
        icon: 'smile',
        component: './Welcome',
      },
      {
        path: '/quan-ly-he-thong/khoi-phuc-du-lieu',
        name: 'khoi-phuc-du-lieu',
        icon: 'smile',
        component: './Welcome',
      },
      {
        component: './404',
      },
    ],
  },
  // {
  //   path: '/import',
  //   name: 'import-excel',
  //   icon: 'smile',
  //   component: './ImportExcel',
  // },
  // {
  //   name: 'list.table-list',
  //   icon: 'table',
  //   path: '/list',
  //   component: './TableList',
  // },
  // {
  //   path: '/admin',
  //   name: 'admin',
  //   icon: 'crown',
  //   access: 'canAdmin',
  //   // component: './Admin',
  //   routes: [
  //     {
  //       path: '/admin/sub-page',
  //       name: 'sub-page',
  //       icon: 'smile',
  //       component: './Admin',
  //     },
  //     {
  //       path: '/admin/user',
  //       name: 'user-manager',
  //       icon: 'smile',
  //       component: './Admin/User'
  //     },
  //     {
  //       component: './404',
  //     },
  //   ],
  // },
  // {
  //   path: '/system',
  //   name: 'system',
  //   icon: 'crown',
  //   access: 'canSystem',
  //   // component: './Admin',
  //   routes: [
  //     {
  //       path: '/system/sub-page',
  //       name: 'sub-page',
  //       icon: 'smile',
  //       component: './Admin',
  //     },
  //     {
  //       component: './404',
  //     },
  //   ],
  // },
  {
    component: './404',
  },
];
